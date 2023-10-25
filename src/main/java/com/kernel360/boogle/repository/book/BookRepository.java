package com.kernel360.boogle.repository.book;

import com.kernel360.boogle.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}