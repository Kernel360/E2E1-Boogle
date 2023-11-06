package com.kernel360.boogle.security.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@ToString
//@Data
//@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberSecurityDTO extends User {

    private Long ID;

    @NotBlank
    @Size(min=6, max=10)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[~!@#$%^&*()_+|}{:?><,./]).{6,10}$",
            message = "비밀번호는 영어 대소문자, 숫자, 특수문자 각각 적어도 1문자를 포함하는 6~10자리 이어야 합니다.")
    private String password;

    @Email
    private String email;

    @NotBlank
    @Size(min=1, max=50)
    private String name;

    @Size(min=2, max=50)
    private String nickname;

    @Pattern(regexp = "^M|W|O$")
    private String gender;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "휴대폰 번호 양식에 맞지 않습니다.")
    private String phoneNumber;

    @Temporal(TemporalType.DATE)
    @FutureOrPresent
    private LocalDateTime signupDate;

    @Temporal(TemporalType.DATE)
    @PastOrPresent
    private LocalDateTime withdrawalDate;

    private String lastModifiedBy;

    //TODO: user_role ENUM으로 선언하고 DB에 연동할 것
//    @Valid
//    private Collection<? extends GrantedAuthority> authorities;

    private String userRole;

    public MemberSecurityDTO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}

