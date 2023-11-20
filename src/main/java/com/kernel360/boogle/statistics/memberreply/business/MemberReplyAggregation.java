package com.kernel360.boogle.statistics.memberreply.business;

import com.kernel360.boogle.member.db.MemberEntity;
import com.kernel360.boogle.reply.db.ReplyEntity;
import com.kernel360.boogle.statistics.memberreply.model.MemberReplyDTO;
import com.kernel360.boogle.statistics.memberreply.service.MemberReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MemberReplyAggregation {
    public List<List<Integer>> getRankUser(List<ReplyEntity> replyEntityList) {
        Map<MemberEntity, Long> memberIdCountMap = replyEntityList.stream()
                .collect(Collectors.groupingBy(ReplyEntity::getMemberEntity, Collectors.counting()));

        Map<Long, Long> newMemberIdCount = memberIdCountMap.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey().getId(), Map.Entry::getValue));

        return newMemberIdCount.entrySet().stream()
                .sorted(Map.Entry.<Long, Long>comparingByValue().reversed())
                .map(entry -> Arrays.asList(entry.getKey().intValue(), entry.getValue().intValue()))
                .toList();
    }
}
