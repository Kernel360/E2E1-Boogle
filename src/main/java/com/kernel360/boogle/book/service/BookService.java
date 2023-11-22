package com.kernel360.boogle.book.service;

import com.kernel360.boogle.book.db.BookEntity;
import com.kernel360.boogle.book.db.BookRepository;
import com.kernel360.boogle.book.model.BookDTO;
import com.kernel360.boogle.book.model.BookViewRequest;
import com.kernel360.boogle.member.model.MemberDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private static final Integer PAGE_SIZE = 6;

    public void createBook(BookDTO book, MemberDTO memberDTO) {
        book.getBookEntity().setCreatedBy(memberDTO.getEmail());
        bookRepository.save(book.getBookEntity());
    }

    public Optional<BookEntity> getBookById(Long bookId) {
        return bookRepository.findById(bookId);
    }

    public Page<BookEntity> getBooks(int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        return bookRepository.findAllByOrderByIdDesc(pageable);
    }

    public Page<BookEntity> getBooksByAuthor(int page, String searchWord) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        return bookRepository.findBookEntitiesByAuthorContainingOrderByIdDesc(searchWord, pageable);
    }

    public Page<BookEntity> getBooksByTitle(int page, String searchWord) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        return bookRepository.findBookEntitiesByTitleContainingOrderByIdDesc(searchWord, pageable);

    }

    public Page<BookEntity> getBooksByPublisher(int page, String searchWord) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        return bookRepository.findBookEntitiesByPublisherContainingOrderByIdDesc(searchWord, pageable);
    }

    public void updateBook(BookDTO book, MemberDTO memberDTO) {
        book.getBookEntity().setLastModifiedBy(memberDTO.getEmail());
        bookRepository.save(book.getBookEntity());
    }

    public void deleteBook(BookViewRequest bookViewRequest) {
        bookRepository.deleteById(bookViewRequest.getId());
    }


}
