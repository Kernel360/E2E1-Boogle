package com.kernel360.boogle.member.service;

import com.kernel360.boogle.member.db.MemberEntity;
import com.kernel360.boogle.member.db.MemberRepository;
import com.kernel360.boogle.member.db.MemberRole;
import com.kernel360.boogle.member.model.MemberSignupDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void signup() {
        //given
        //when
        MemberEntity member = memberService.signup(
                MemberSignupDTO.builder()
                .email("kkk@gmail.com")
                .password("password")
                .name("김커널")
                .nickname("커널킴")
                .gender("M")
                .birthdate(LocalDate.of(2000, 1, 1))
                .phoneNumber("010-1111-2222")
                .build());
        //then
        then(member.getId()).isNotNull(); // id가 NotNull인지 검증
        then(member.getEmail()).isEqualTo("kkk@gmail.com"); // 유저명이 user123인지 검증
        then(member.getUsername()).isEqualTo("김커널"); // 유저명이 user123인지 검증
        then(member.getPassword()).startsWith("{bcrypt}"); // 패스워드가 {bcrypt}로 시작하는지 검증
        then(member.getRole()).isEqualTo(MemberRole.ROLE_USER);
//        then(member.getAuthorities()).hasSize(1); // Authorities가 1개인지 검증
//        then(member.getAuthorities().stream().findFirst().get().getAuthority()).isEqualTo("ROLE_USER");
//        then(member.isAdmin()).isFalse(); // 어드민 여부가 False인지 검증
//        then(member.isAccountNonExpired()).isTrue();
//        then(member.isAccountNonLocked()).isTrue();
//        then(member.isEnabled()).isTrue();
//        then(member.isCredentialsNonExpired()).isTrue();
    }

    @Test
    void findByEmail() {
        MemberEntity member = memberRepository.findByEmail("boogle@gmail.com");
        assertEquals("김부글", member.getName());
    }
}