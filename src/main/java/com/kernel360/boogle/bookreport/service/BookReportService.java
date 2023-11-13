package com.kernel360.boogle.bookreport.service;

import com.kernel360.boogle.bookreport.db.BookReportEntity;
import com.kernel360.boogle.bookreport.db.BookReportRepository;
import com.kernel360.boogle.bookreport.model.BookReportDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class BookReportService {

    private final BookReportRepository bookReportRepository;

    private static final Integer PAGE_SIZE = 6;

    public BookReportService(BookReportRepository bookReportRepository) {
        this.bookReportRepository = bookReportRepository;
    }

    public void createBookReport(BookReportDTO bookReport) {
        bookReport.getBookReportEntity().setCreatedBy("TEST"); // 로그인 사용자 정보 들어가야 함
        bookReport.getBookReportEntity().setMemberId(1L); // 로그인 사용자 정보 들어가야 함
        bookReportRepository.save(bookReport.getBookReportEntity());
    }

    public Optional<BookReportEntity> getBookReportById(Long bookReportId) {
        return bookReportRepository.findById(bookReportId);
    }

    public Page<BookReportEntity> getPublicBookReports(String isPublic, int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        return bookReportRepository.findAllByIsPublicEqualsAndIsDeletedNotOrderByIdDesc(isPublic, "Y", pageable);
    }

    public Page<BookReportEntity> getMyBookReports(Long memberId, int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        return bookReportRepository.findAllByMemberIdEqualsAndIsDeletedNotOrderByIdDesc(memberId, "Y", pageable);
    }

    public Page<BookReportEntity> getAllBookReports(int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        return bookReportRepository.findAllByIsDeletedNotOrderByIdDesc("Y", pageable);
    }

    public void updateBookReport(BookReportDTO bookReport) {
        bookReport.getBookReportEntity().setLastModifiedBy("TEST"); // 로그인 사용자 정보 들어가야 함
        bookReport.getBookReportEntity().setMemberId(1L); // 로그인 사용자 정보 들어가야 함
        bookReport.getBookReportEntity().setCreatedBy("TEST"); // 로그인 사용자 정보 들어가야 함
        bookReportRepository.save(bookReport.getBookReportEntity());
    }

    public void deleteBookReport(Long bookReportId) {
        bookReportRepository.findById(bookReportId)
                .map(
                        it -> {
                            it.setIsDeleted("Y");
                            it.setDeletedAt(LocalDateTime.now());
                            bookReportRepository.save(it);
                            return it;
                        }
                );
    }



}
