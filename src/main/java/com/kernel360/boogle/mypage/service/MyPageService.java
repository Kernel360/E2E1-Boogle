package com.kernel360.boogle.mypage.service;

import com.kernel360.boogle.member.db.MemberEntity;
import com.kernel360.boogle.mypage.model.MemberRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MyPageService {

    private final PasswordEncoder passwordEncoder;

    public boolean validatePassword(MemberEntity memberEntity, MemberRequestDTO member) {
        if (memberEntity == null || member == null) {
            return false;
        }
        return passwordEncoder.matches(member.getPassword(), memberEntity.getPassword());
    }
}
