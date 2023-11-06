package com.kernel360.boogle.security.service;

import com.kernel360.boogle.member.db.MemberEntity;
import com.kernel360.boogle.member.db.MemberRepository;
import com.kernel360.boogle.security.model.MemberSecurityDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername: " + username);

        Optional<MemberEntity> result = memberRepository.findByName(username);

        if (result.isEmpty())
            throw new UsernameNotFoundException("username is not found...");

        MemberEntity member = result.get();

        MemberSecurityDTO memberSecurityDTO =
                MemberSecurityDTO
                        .builder()
                        .ID(member.getId())
                        .password(member.getPassword())
                        .email(member.getEmail())
                        .name(member.getName())
                        .nickname(member.getNickname())
                        .gender(member.getGender())
                        .birthdate(member.getBirthdate())
                        .phoneNumber(member.getPhoneNumber())
                        .signupDate(member.getSignupDate())
                        .withdrawalDate(member.getWithdrawalDate())
                        .lastModifiedBy(member.getLastModifiedBy())
                        .userRole(member.getUserRole())
                        .build();

        log.info("memberSecurityDTO");
        log.info(memberSecurityDTO);

        return memberSecurityDTO;


    }


}
