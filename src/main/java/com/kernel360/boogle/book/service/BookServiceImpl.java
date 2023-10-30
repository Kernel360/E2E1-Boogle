package com.kernel360.boogle.book.service;

import com.kernel360.boogle.book.db.BookEntity;
import com.kernel360.boogle.book.db.BookRepository;
import com.kernel360.boogle.book.model.BookDTO;
import com.kernel360.boogle.book.model.BookRequest;
import com.kernel360.boogle.book.model.BookViewRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private static final String SEARCH_TYPE_TITLE = "title";
    private static final String SEARCH_TYPE_AUTHOR = "author";
    private static final String SEARCH_TYPE_PUBLISHER = "publisher";

    @Override
    public void createBook(BookDTO book) {
        book.getBookEntity().setCreatedBy("TEST"); // 로그인 사용자 정보 들어가야 함
        bookRepository.save(book.getBookEntity());
    }

    @Override
    public void updateBook(BookDTO book) {
        book.getBookEntity().setLastModifiedBy("TEST"); // 로그인 사용자 정보 들어가야함
        bookRepository.save(book.getBookEntity());
    }

    @Override
    public List<BookDTO> findAladinBook(BookRequest request) {

        return null;
    }

    @Override
    public Optional<BookEntity> findById(Long bookId) {
        return bookRepository.findById(bookId);
    }

    @Override
    public Page<BookEntity> getAllBooks(int page, String searchWord, String searchType) {
        Pageable pageable = PageRequest.of(page, 6);

        return switch (searchType) {
            case SEARCH_TYPE_TITLE ->
                    bookRepository.findBookEntitiesByBookTitleContainingAndIsDeletedNotOrderByBookIdDesc(searchWord, pageable, "Y");
            case SEARCH_TYPE_AUTHOR ->
                    bookRepository.findBookEntitiesByAuthorContainingAndIsDeletedNotOrderByBookIdDesc(searchWord, pageable, "Y");
            case SEARCH_TYPE_PUBLISHER ->
                    bookRepository.findBookEntitiesByPublisherContainingAndIsDeletedNotOrderByBookIdDesc(searchWord, pageable, "Y");
            default -> bookRepository.findAllByIsDeletedNotOrderByBookIdDesc(pageable, "Y");
        };
    }

    @Override
    public void deleteBook(
            @RequestBody BookViewRequest bookViewRequest) {
        bookRepository.findById(bookViewRequest.getBookId())
                .map(
                        it -> {
                            it.setIsDeleted("Y");
                            bookRepository.save(it);
                            return it;
                        }
                );
    }
}
