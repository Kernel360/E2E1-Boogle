package com.kernel360.boogle.member.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 유저 회원가입용 Dto
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class MemberSignupDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String nickname;
    private String gender;
    private LocalDate birthdate;
    private String phoneNumber;
    private LocalDateTime signupDate;

}
