//package com.kernel360.boogle.member.db;
//
//import lombok.extern.log4j.Log4j2;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import javax.transaction.Transactional;
//import java.time.LocalDate;
//import java.util.Optional;
//
//
//@SpringBootTest
//@Log4j2
//class MemberRepositoryTest {
//
//    @Autowired
//    private MemberRepository memberRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Test
//    @Transactional
//    void insertMembers(){
//
//        for (long i = 1; i <= 10; i++) {
//            MemberEntity member = MemberEntity.builder()
//                    .id(i)
//                    .password(passwordEncoder.encode("1111"))
//                    .email("email" + i + "@aaa.bbb")
//                    .gender("men")
//                    .birthdate(LocalDate.now())
//                    .phoneNumber("010-111-1111")
//                    .nickname("wonsangZZang" + i)
//                    .build();
//
//            member.addRole(MemberRole.USER);
//            if (i >= 8)
//                member.addRole(MemberRole.ADMIN);
//            memberRepository.save(member);
//
//        }
//
//
//    }
//
//
////    @Test
////    public void testRead() {
////        Optional<MemberEntity> result = memberRepository.getWithRoles("member100");
////        MemberEntity member = result.orElseThrow();
////
////        log.info(member);
////        log.info(member.getRoleSet());
////
////        member.getRoleSet().forEach(memberRole -> log.info(memberRole.name()));
////    }
//
//
//
//}
//    @Transactional
//    void insertMembers(){
//
//        for (long i = 1; i <= 10; i++) {
//            MemberEntity member = MemberEntity.builder()
//                    .id(i)
//                    .password(passwordEncoder.encode("1111"))
//                    .email("email" + i + "@aaa.bbb")
//                    .gender("men")
//                    .birthdate(LocalDate.now())
//                    .phoneNumber("010-111-1111")
//                    .nickname("wonsangZZang" + i)
//                    .build();
//
//            member.addRole(MemberRole.USER);
//            if (i >= 8)
//                member.addRole(MemberRole.ADMIN);
//            memberRepository.save(member);
//
//        }
//
//
//    }
//
//
//    @Test
//    public void testRead() {
//        Optional<MemberEntity> result = memberRepository.getWithRoles("member100");
//        MemberEntity member = result.orElseThrow();
//
//        log.info(member);
//        log.info(member.getRoleSet());
//
//        member.getRoleSet().forEach(memberRole -> log.info(memberRole.name()));
//    }
//
//
//
//}
