package com.kernel360.boogle.reply.service;

import com.kernel360.boogle.bookreport.model.BookReportDTO;
import com.kernel360.boogle.reply.db.ReplyEntity;
import com.kernel360.boogle.reply.model.ReplyDTO;

import java.util.List;
import java.util.Optional;

public interface ReplyService {
    public void createReply(ReplyDTO reply);

    Optional<List<ReplyEntity>> getRepliesByBookReport(Long bookReportId);

    public void updateReply(ReplyDTO reply);

    public void deleteReply(Long replyId);
}
