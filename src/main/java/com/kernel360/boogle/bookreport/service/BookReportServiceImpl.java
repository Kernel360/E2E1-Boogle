package com.kernel360.boogle.bookreport.service;

import com.kernel360.boogle.bookreport.db.BookReportEntity;
import com.kernel360.boogle.bookreport.db.BookReportRepository;
import com.kernel360.boogle.bookreport.model.BookReportDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class BookReportServiceImpl implements BookReportService {

    private final BookReportRepository bookReportRepository;

    public BookReportServiceImpl(BookReportRepository bookReportRepository) {
        this.bookReportRepository = bookReportRepository;
    }

    @Override
    public void createBookReport(BookReportDTO bookReport) {

    }

    @Override
    public Optional<BookReportEntity> getBookReportById(Long bookReportId) {
        return Optional.empty();
    }

    @Override
    public Page<BookReportEntity> getAllBookReports(String isPublic, int page) {
        return null;
    }

    @Override
    public Page<BookReportEntity> getMyBookReports(Long memberId, int page) {
        return null;
    }

    @Override
    public void updateBookRepoert(BookReportDTO bookReport) {

    }

    @Override
    public void deleteBookReport(Long bookReportId) {

    }
}
