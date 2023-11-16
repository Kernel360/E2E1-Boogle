package com.kernel360.boogle.member.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    MemberEntity findByEmail(String email);

    Optional<MemberEntity> findById(Long Id);

    List<MemberEntity> findAllBySignupDateBetween(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );


}