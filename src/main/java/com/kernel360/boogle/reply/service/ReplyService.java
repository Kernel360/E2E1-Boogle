package com.kernel360.boogle.reply.service;

import com.kernel360.boogle.reply.model.ReplyDTO;

import java.util.List;
import java.util.Optional;

public interface ReplyService {
    public void createReply(ReplyDTO reply);

    Optional<List<ReplyDTO>> getRepliesByBookReportId(Long bookReportId);

    public void updateReply(ReplyDTO reply);

    public void deleteReply(ReplyDTO reply);
}
