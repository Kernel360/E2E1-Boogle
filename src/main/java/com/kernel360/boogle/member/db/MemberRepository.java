package com.kernel360.boogle.member.db;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {

    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from MEMBER m")
    Optional<MemberEntity> getWithRoles (String mid);

}
