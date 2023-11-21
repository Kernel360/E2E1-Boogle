package com.kernel360.boogle.bookreport.service;

import com.kernel360.boogle.bookreport.db.BookReportEntity;
import com.kernel360.boogle.bookreport.db.BookReportRepository;
import com.kernel360.boogle.bookreport.model.BookReportDTO;
import com.kernel360.boogle.member.model.MemberDTO;
import com.kernel360.boogle.reply.db.ReplyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookReportService {

    private final BookReportRepository bookReportRepository;
    private final ReplyRepository replyRepository;

    private static final Integer PAGE_SIZE = 6;

    public BookReportService(BookReportRepository bookReportRepository, ReplyRepository replyRepository) {
        this.bookReportRepository = bookReportRepository;
        this.replyRepository = replyRepository;
    }

    public void createBookReport(BookReportDTO bookReport, MemberDTO memberDTO) {
        bookReport.getBookReportEntity().setCreatedBy(memberDTO.getEmail());
        bookReport.getBookReportEntity().setMemberEntity(memberDTO.getMemberEntity());
        bookReportRepository.save(bookReport.getBookReportEntity());
    }

    public Optional<BookReportEntity> getBookReportById(Long bookReportId) {
        return bookReportRepository.findById(bookReportId);
    }

    public Page<BookReportEntity> getPublicBookReports(String isPublic, int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        return bookReportRepository.findAllByIsPublicOrderByIdDesc(isPublic, pageable);
    }

    public Page<BookReportEntity> getMyBookReports(Long memberId, int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        return bookReportRepository.findAllByMemberEntity_IdOrderByIdDesc(memberId, pageable);
    }

    public Page<BookReportEntity> getAllBookReports(int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        return bookReportRepository.findAllByOrderByIdDesc(pageable);
    }

    public List<BookReportEntity> getPublicBookReportsByBookId(Long bookId) {
        return bookReportRepository.findAllByBookEntity_IdAndIsPublicOrderByCreatedAtDesc(bookId,"Y");
    }

    public void updateBookReport(BookReportDTO bookReport, MemberDTO memberDTO) {
        bookReport.getBookReportEntity().setLastModifiedBy(memberDTO.getEmail());
        bookReport.getBookReportEntity().setMemberEntity(memberDTO.getMemberEntity());
        System.out.println("bookReport = " + bookReport);
        bookReportRepository.save(bookReport.getBookReportEntity());
    }

    public void deleteBookReport(Long bookReportId){
        replyRepository.deleteAllByBookReportEntity_id(bookReportId);
        bookReportRepository.deleteById(bookReportId);
    }


    public List<BookReportEntity> getBookReportsByMemberId(Long memberId) {
        return bookReportRepository.findAllByMemberEntity_Id(memberId);
    }
}
