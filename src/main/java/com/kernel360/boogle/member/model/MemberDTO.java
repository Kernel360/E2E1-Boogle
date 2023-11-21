package com.kernel360.boogle.member.model;

import com.kernel360.boogle.member.db.MemberEntity;
import com.kernel360.boogle.member.db.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String phoneNumber;
    private LocalDateTime signupDate;
    private LocalDateTime withdrawalDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedAt;
    private MemberRole role;
    private MemberEntity memberEntity;

    public static MemberDTO from(MemberEntity memberEntity) {
        return new MemberDTO(
                memberEntity.getId(),
                memberEntity.getEmail(),
                memberEntity.getPassword(),
                memberEntity.getName(),
                memberEntity.getNickname(),
                memberEntity.getPhoneNumber(),
                memberEntity.getSignupDate(),
                memberEntity.getWithdrawalDate(),
                memberEntity.getLastModifiedBy(),
                memberEntity.getLastModifiedAt(),
                memberEntity.getRole(),
                memberEntity
        );
    }
}
