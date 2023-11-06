package com.kernel360.boogle.member.db;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {
    @EntityGraph(attributePaths = "roleSet")
    Optional<MemberEntity> findByName(String userName);

    Optional<MemberEntity> findById(Long memberId);



}
