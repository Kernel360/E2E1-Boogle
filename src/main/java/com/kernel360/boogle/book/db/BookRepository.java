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
    List<BookEntity> findBookEntitiesByBookTitleContaining(String bookTitle);
    List<BookEntity> findBookEntitiesByAuthorContaining(String author);
    List<BookEntity> findBookEntitiesByPublisherContaining(String publisher);
    Optional<BookEntity> findById(Long bookId);

}
