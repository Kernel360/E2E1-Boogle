package com.kernel360.boogle.repository.book;

import com.kernel360.boogle.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
