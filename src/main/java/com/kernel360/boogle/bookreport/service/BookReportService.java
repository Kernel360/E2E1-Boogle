package com.kernel360.boogle.bookreport.service;

import com.kernel360.boogle.bookreport.db.BookReportEntity;
import com.kernel360.boogle.bookreport.model.BookReportDTO;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface BookReportService {
    public void createBookReport(BookReportDTO bookReport);

    Optional<BookReportEntity> getBookReportById(Long bookReportId);

    Page<BookReportEntity> getPublicBookReports(String isPublic, int page);

    Page<BookReportEntity> getMyBookReports(Long memberId, int page);

    Page<BookReportEntity> getAllBookReports(int page);

    public void updateBookRepoert(BookReportDTO bookReport);

    public void deleteBookReport(Long bookReportId);



}
