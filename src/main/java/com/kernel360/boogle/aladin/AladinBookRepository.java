package com.kernel360.boogle.aladin;


import com.kernel360.boogle.book.db.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AladinBookRepository extends JpaRepository<BookEntity, Long> {
}
