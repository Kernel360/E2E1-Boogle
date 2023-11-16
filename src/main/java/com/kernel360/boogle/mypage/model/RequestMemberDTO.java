package com.kernel360.boogle.mypage.model;


import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@Data
public class RequestMemberDTO {

    private String name;
    private String password;
    private String email;
    private String nickname;
    private String phoneNumber;

}
