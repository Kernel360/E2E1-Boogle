package com.kernel360.boogle.member.service;

import com.kernel360.boogle.member.db.MemberEntity;
import com.kernel360.boogle.member.db.MemberRepository;
import com.kernel360.boogle.member.db.MemberRole;
import com.kernel360.boogle.member.exception.AlreadySignedupMemberException;
import com.kernel360.boogle.member.model.MemberSignupDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberEntity signup(MemberSignupDTO member) {
        if (memberRepository.findByEmail(member.getEmail()) != null) {
            throw new AlreadySignedupMemberException();
        }

        return memberRepository.save(MemberEntity.builder()
                                        .email(member.getEmail())
                                        .password(passwordEncoder.encode(member.getPassword()))
                                        .name(member.getName())
                                        .nickname(member.getNickname())
                                        .gender(member.getGender())
                                        .birthdate(member.getBirthdate())
                                        .phoneNumber(member.getPhoneNumber())
                                        .role(MemberRole.ROLE_USER)
                                        .build());
    }

    public MemberEntity signupAdmin(MemberSignupDTO member) {
        if (memberRepository.findByEmail(member.getEmail()) != null) {
            throw new AlreadySignedupMemberException();
        }

        return memberRepository.save(MemberEntity.builder()
                                        .email(member.getEmail())
                                        .password(passwordEncoder.encode(member.getPassword()))
                                        .name(member.getName())
                                        .nickname(member.getNickname())
                                        .gender(member.getGender())
                                        .birthdate(member.getBirthdate())
                                        .phoneNumber(member.getPhoneNumber())
                                        .role(MemberRole.ROLE_ADMIN)
                                        .build());
    }

    public MemberEntity findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
}