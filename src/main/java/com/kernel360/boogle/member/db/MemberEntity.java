package com.kernel360.boogle.member.db;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "MEMBER")
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = "roleSet")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "phone_number")
    private String phoneNumber;

    @CreatedDate
    @Column(name = "signup_date", columnDefinition = "DATETIME", updatable = false)
    private LocalDateTime signupDate;

    @Column(name = "withdrawal_date", columnDefinition = "DATETIME")
    private LocalDateTime withdrawalDate;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_at", columnDefinition = "DATETIME")
    private LocalDateTime lastModifiedAt;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    @Column(name = "role_set")
    @Enumerated(EnumType.STRING)
    private Set<MemberRole> roleSet = new HashSet<>();

    public void addRole(MemberRole memberRole) {
        this.roleSet.add(memberRole);
    }

    public void clearRole() {
        this.roleSet.clear();
    }
}
