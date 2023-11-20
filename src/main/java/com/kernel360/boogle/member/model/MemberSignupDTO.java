package com.kernel360.boogle.member.model;

import lombok.*;

import javax.validation.constraints.*;
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

    @NotBlank(message="이름은 의무 입력입니다.")
    private String name;

    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀번호를 입력하세요.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$",
            message = "비밀번호는 최소 8자 이상이어야 하며, 최소 하나의 숫자, 하나의 영문자, 그리고 하나의 특수 문자를 포함해야 합니다.")
    private String password;

    @Size(min = 2, max = 10, message = "닉네임은 2 ~ 10 글자만 가능합니다.")
    private String nickname;

    @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "전화번호형식은 000-0000-0000 입니다.")
    private String phoneNumber;

    @PastOrPresent(message = "등록일은 미래값이 될 수 없습니다.")
    private LocalDateTime signupDate;

}
