package com.kernel360.boogle.mypage.model;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class MemberRequestDTO {

    @NotBlank(message = "이름은 의무 입력입니다.")
    @Size(min = 2, max = 5, message = "이름은 2~5자 사이여야 합니다.")
    private String name;

    @NotBlank(message = "비밀번호를 입력하세요.")
    @Pattern(regexp = "^[0-9a-zA-Z]{6,}$",
            message = "비밀번호는 최소 6자 이상이어야 하며, 숫자나 영어만 포함해야 합니다.")
    private String password;

    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    @Size(min = 2, max = 10, message = "닉네임은 2 ~ 10 글자만 가능합니다.")
    private String nickname;

    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "전화번호형식은 000-0000-0000 입니다.")
    private String phoneNumber;

}





