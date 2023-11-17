package com.kernel360.boogle.statistics.memberreply.scheduler.legacy;

import com.kernel360.boogle.reply.db.ReplyEntity;
import com.kernel360.boogle.reply.db.ReplyRepository;
import com.kernel360.boogle.statistics.memberreply.business.MemberReplyAggregation;
import com.kernel360.boogle.statistics.memberreply.model.MemberReplyDTO;
import com.kernel360.boogle.statistics.memberreply.service.MemberReplyConverter;
import com.kernel360.boogle.statistics.memberreply.service.MemberReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
// import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberReplySchedulerLegacy {
    private final ReplyRepository replyRepository;
    private final MemberReplyService memberReplyService;
    private final MemberReplyAggregation memberReplyAggregation;

    // @Scheduled(fixedRate = 1000 * 1000)
    public void scheduleMonthlyRank() {
        LocalDateTime currentDate = LocalDateTime.now();
        for (int i=5; i>=1; i--) {
            LocalDateTime lastMonthFirstDay = currentDate.minusMonths(i).withDayOfMonth(1);
            List<ReplyEntity> replyList = replyRepository.findAllByCreatedAtBetween(lastMonthFirstDay, currentDate);
            List<List<Integer>> rankUsers = memberReplyAggregation.getRankUser(replyList);
            IntStream.range(0, rankUsers.size())
                    .forEach(j -> {
                        List<Integer> replyAggregation = rankUsers.get(j);

                        System.out.println(MemberReplyDTO.builder()
                                .memberId(Long.valueOf(replyAggregation.get(0)))
                                .replyCount(replyAggregation.get(1))
                                .recordDate(lastMonthFirstDay.toLocalDate())
                                .rank(j + 1) // 1부터 시작하는 인덱스 사용
                                .build());

                        memberReplyService.saveMemberReply(
                                MemberReplyDTO.builder()
                                        .memberId(Long.valueOf(replyAggregation.get(0)))
                                        .replyCount(replyAggregation.get(1))
                                        .recordDate(lastMonthFirstDay.toLocalDate())
                                        .rank(j + 1) // 1부터 시작하는 인덱스 사용
                                        .build(),
                                MemberReplyConverter::toEntity
                        );
                    });
        }
    }

}
