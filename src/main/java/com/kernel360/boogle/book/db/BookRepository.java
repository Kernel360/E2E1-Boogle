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
    Page<BookEntity> findBookEntitiesByBookTitleContainingAndIsDeletedNotOrderByBookIdDesc(String title, Pageable pageable, String isDeleted);
    Page<BookEntity> findBookEntitiesByAuthorContainingAndIsDeletedNotOrderByBookIdDesc(String author, Pageable pageable, String isDeleted);
    Page<BookEntity> findBookEntitiesByPublisherContainingAndIsDeletedNotOrderByBookIdDesc(String publisher, Pageable pageable, String isDeleted);
    Optional<BookEntity> findById(Long bookId);
    Page<BookEntity> findAllByIsDeletedNotOrderByBookIdDesc(Pageable pageable, String isDeleted);
}
