package com.kernel360.boogle.global.jwt;

import com.kernel360.boogle.member.db.MemberEntity;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;

import java.security.Key;
import java.util.Date;

@Slf4j
public class JwtUtils {
    /**
     * 토큰에서 username 찾기
     *
     * @param token 토큰
     * @return username
     */
    public static String getUsername(String token) {
        // jwtToken에서 username을 찾습니다.
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKeyResolver(SigningKeyResolver.instance)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject();
        } catch (ExpiredJwtException e) {
            log.debug("jwt 토큰 만료");
            return null;
        } catch (Exception e) {
            log.error("jwt 토큰 처리중 Error 발생!:" + e.getMessage());
            return null;
        }
    }

    /**
     * user로 토큰 생성
     * HEADER : alg, kid
     * PAYLOAD : sub, iat, exp
     * SIGNATURE : JwtKey.getRandomKey로 구한 Secret Key로 HS512 해시
     *
     * @param member 회원
     * @return jwt token
     */
//    public static String createToken(MemberEntity member) {
//        Claims claims = Jwts.claims().setSubject(member.getEmail()); // subject
//        Date now = new Date(); // 현재 시간
//        Pair<String, Key> key = JwtKey.getRandomKey();
//        // JWT Token 생성
//        return Jwts.builder()
//                .setClaims(claims) // 정보 저장
//                .setIssuedAt(now) // 토큰 발행 시간 정보
//                .setExpiration(new Date(now.getTime() + JwtProperties.EXPIRATION_TIME)) // 토큰 만료 시간 설정
//                .setHeaderParam(JwsHeader.KEY_ID, key.getFirst()) // kid
//                .signWith(key.getSecond()) // signature
//                .compact();
//    }
    public static String createToken(MemberEntity member) { //유저 가져와서 유저에 맞는 토큰을 생성해줌.
        Claims claims = Jwts.claims().setSubject(member.getEmail()); // subject
        Date now = new Date(); // 현재 시간
        Pair<String, Key> key = JwtKey.getRandomKey();
        // JWT Token 생성
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보 -> 지금 발행되니까 now
                .setExpiration(new Date(now.getTime() + JwtProperties.EXPIRATION_TIME)) // 토큰 만료 시간 설정 -> 코드 상에서는 10분
                .setHeaderParam(JwsHeader.KEY_ID, key.getFirst()) // kid
                .signWith(key.getSecond()) // signature
                .compact(); //compact 통해 토큰 생성
    }

    public static boolean validateToken(String token) {
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

            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public static String createRefreshToken(MemberEntity member) {
        Date now = new Date();
        Pair<String, Key> key = JwtKey.getRandomKey();

        return Jwts.builder()
                .setSubject(member.getEmail())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + JwtProperties.REFRESH_EXPIRATION_TIME))
                .setHeaderParam(JwsHeader.KEY_ID, key.getFirst())
                .signWith(key.getSecond(), SignatureAlgorithm.HS512)
                .compact();
    }

    public static boolean validateRefreshToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKeyResolver(SigningKeyResolver.instance)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
