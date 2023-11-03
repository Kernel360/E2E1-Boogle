package com.kernel360.boogle.bookreport.db;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookReportRepository extends JpaRepository<BookReportEntity, Long> {
    BookReportEntity save(BookReportEntity bookReport);

    Page<BookReportEntity> findAllByIsPublicEqualsAndIsDeletedNotOrderByIdDesc(String isPublic, String isDeleted, Pageable pageable);

    Page<BookReportEntity> findAllByMemberIdEqualsAndIsDeletedNotOrderByIdDesc(Long memberId, String isDeleted, Pageable pageable);

    Page<BookReportEntity> findAllByIsDeletedNotOrderByIdDesc(String isDeleted, Pageable pageable);

    Optional<BookReportEntity> findById(Long bookReportId);




}
