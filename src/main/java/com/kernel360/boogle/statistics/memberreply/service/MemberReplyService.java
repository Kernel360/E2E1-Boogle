package com.kernel360.boogle.statistics.memberreply.service;

import com.kernel360.boogle.statistics.memberreply.db.MemberReplyEntity;
import com.kernel360.boogle.statistics.memberreply.db.MemberReplyRepository;
import com.kernel360.boogle.statistics.memberreply.model.MemberReplyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class MemberReplyService {

    private final MemberReplyRepository memberReplyRepository;

    private final MemberReplyConverter memberReplyConverter;

    public void saveMemberReply(MemberReplyDTO memberReplyDTO, Function<MemberReplyDTO, MemberReplyEntity> converter) {
        memberReplyRepository.save(converter.apply(memberReplyDTO));
    }

    public List<MemberReplyDTO> getMemberReplyList(LocalDate startOfTheMonth) {
        final int minRank = 1;
        final int maxRank = 5;
        return  memberReplyConverter.toDTOList(
                memberReplyRepository.findByRecordDateAndReplyRankBetween(startOfTheMonth, minRank, maxRank)
        );
    }

}
