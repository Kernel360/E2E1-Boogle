package com.kernel360.boogle.bookreport.db;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookReportRepository extends JpaRepository<BookReportEntity, Long> {
    BookReportEntity save(BookReportEntity bookReport);

    Page<BookReportEntity> findAllByIsPublicOrderByIdDesc(String isPublic, Pageable pageable);

    Page<BookReportEntity> findAllByMemberEntity_IdOrderByIdDesc(Long memberId, Pageable pageable);

    Page<BookReportEntity> findAllByOrderByIdDesc(Pageable pageable);

    Optional<BookReportEntity> findById(Long bookReportId);

    List<BookReportEntity> findAllByBookEntity_IdAndIsPublicOrderByCreatedAtDesc(Long bookId, String isPublic);

    List<BookReportEntity> findAllByMemberEntity_Id(Long memberId);

    void deleteById(Long id);
}
