package com.kernel360.boogle.book.db;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    BookEntity save(BookEntity book);
    List<BookEntity> findAll();
    Page<BookEntity> findAll(Pageable pageable);
    Page<BookEntity> findBookEntitiesByTitleContainingOrderByIdDesc(String title, Pageable pageable);
    Page<BookEntity> findBookEntitiesByAuthorContainingOrderByIdDesc(String author, Pageable pageable);
    Page<BookEntity> findBookEntitiesByPublisherContainingOrderByIdDesc(String publisher, Pageable pageable);
    Optional<BookEntity> findById(Long bookId);
    Page<BookEntity> findAllByOrderByIdDesc(Pageable pageable);
}
