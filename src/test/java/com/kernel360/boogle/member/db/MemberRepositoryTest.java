package com.kernel360.boogle.member.db;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@Log4j2
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Test
//    @Transactional
//    void insertMembers(){
//
//        LongStream.rangeClosed(1,10)
//                .forEach(i -> {
//                    MemberEntity member = MemberEntity.builder()
//                            .id(i)
//                            .password(passwordEncoder.encode("1111"))
//                            .email("email"+i+"@aaa.bbb")
//                            .gender("men")
//                            .birthdate(LocalDate.now())
//                            .phoneNumber("010-111-1111")
//                            .nickname("wonsangZZang"+i)
//                            .build();
//
//                    member.addRole(MemberRole.USER);
//                    if (i>= 8)
//                        member.addRole(MemberRole.ADMIN);
//
//                    memberRepository.save(member);
//                });
//
//    }



}
