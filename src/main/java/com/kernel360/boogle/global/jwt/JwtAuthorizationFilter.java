package com.kernel360.boogle.global.jwt;

import com.kernel360.boogle.member.db.MemberEntity;
import com.kernel360.boogle.member.service.MemberService;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.function.Function;

/**
 * JWT를 이용한 인증
 */
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final MemberService memberService;

    public JwtAuthorizationFilter (MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        String accessToken = null;
        String refreshToken = null;

        try {
            Cookie[] cookies = request.getCookies();
            if(cookies != null) {
                for(Cookie cookie : cookies) {
                    if(cookie.getName().equals(JwtProperties.COOKIE_NAME)) {
                        accessToken = cookie.getValue();
                    } else if (cookie.getName().equals(JwtProperties.REFRESH_COOKIE_NAME)) {
                        refreshToken = cookie.getValue();
                    }
                }
            }
        } catch (Exception ignored) {
        }

        boolean validAccessToken = false;
        if(accessToken != null) {
            try {
                validAccessToken = JwtUtils.validateToken(accessToken);
                if(validAccessToken) {
                    Authentication authentication = getUsernamePasswordAuthenticationToken(accessToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception ignored) {
            }
        }

        if(!validAccessToken && refreshToken != null && JwtUtils.validateRefreshToken(refreshToken)) {
            String userEmail = getUserEmailFromRefreshToken(refreshToken);
            try {
                MemberEntity member = memberService.findByEmail(userEmail);
                String newAccessToken = JwtUtils.createToken(member);
                Authentication newAuth = new UsernamePasswordAuthenticationToken(member, null, member.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(newAuth);

                Cookie newAccessTokenCookie = new Cookie(JwtProperties.COOKIE_NAME, newAccessToken);
                newAccessTokenCookie.setPath("/");
                newAccessTokenCookie.setMaxAge(JwtProperties.EXPIRATION_TIME);
                response.addCookie(newAccessTokenCookie);
            } catch(Exception ignored) {
            }
        } else if (!validAccessToken) {
            Cookie cookie = new Cookie(JwtProperties.COOKIE_NAME, null);
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthenticationToken(String token) {
        String userName = JwtUtils.getUsername(token);
        if (userName != null) {
            MemberEntity member = memberService.findByEmail(userName);
            return new UsernamePasswordAuthenticationToken(
                    member, // principal
                    null,
                    member.getAuthorities()
            );
        }
        return null; // 회원이 없으면 NULL
    }
    private String getUserEmailFromRefreshToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private static <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKeyResolver(new SigningKeyResolverAdapter() {
                        @Override
                        public Key resolveSigningKey(JwsHeader header, Claims claims) {
                            String kid = header.getKeyId();
                            return JwtKey.getKey(kid);
                        }
                    })
                    .build()
                    .parseClaimsJws(token);

            return claimsResolver.apply(claims.getBody());
        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }
}