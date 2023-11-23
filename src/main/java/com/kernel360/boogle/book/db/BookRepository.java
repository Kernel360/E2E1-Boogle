package com.kernel360.boogle.book.db;



import com.kernel360.boogle.member.db.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    BookEntity save(BookEntity book);
    List<BookEntity> findAll();
    Page<BookEntity> findAll(Pageable pageable);
    Page<BookEntity> findBookEntitiesByTitleContainingOrderByIdDesc(String title, Pageable pageable);
    Page<BookEntity> findBookEntitiesByAuthorContainingOrderByIdDesc(String author, Pageable pageable);
    Page<BookEntity> findBookEntitiesByPublisherContainingOrderByIdDesc(String publisher, Pageable pageable);
    List<BookEntity> findBookEntitiesByTitleIsContainingOrderByTitleAsc(String title);
    Optional<BookEntity> findById(Long bookId);
    Page<BookEntity> findAllByOrderByIdDesc(Pageable pageable);
    List<BookEntity> findAllByPublishDateBetween(
            @Param("start") LocalDate start,
            @Param("end") LocalDate end
    );

    void deleteById(Long id);
}
