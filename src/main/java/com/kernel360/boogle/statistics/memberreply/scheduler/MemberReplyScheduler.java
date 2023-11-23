package com.kernel360.boogle.statistics.memberreply.scheduler;

import com.kernel360.boogle.global.batch.MailForNewRelease;
import com.kernel360.boogle.reply.db.ReplyEntity;
import com.kernel360.boogle.reply.db.ReplyRepository;
import com.kernel360.boogle.statistics.memberreply.business.MemberReplyAggregation;
import com.kernel360.boogle.statistics.memberreply.model.MemberReplyDTO;
import com.kernel360.boogle.statistics.memberreply.service.MemberReplyConverter;
import com.kernel360.boogle.statistics.memberreply.service.MemberReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberReplyScheduler {

    private final ReplyRepository replyRepository;
    private final MemberReplyService memberReplyService;
    private final MemberReplyAggregation memberReplyAggregation;
    private final MailForNewRelease mailForNewRelease;


    /**
     * 매월 1일 새벽 2시에 그 전월 MemberReply를 업데이트 할 것<br>
     */
    @Scheduled(cron="0 0 2 1 * ?")
    public void scheduleMonthlyRank() {
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime lastMonthFirstDay = currentDate.minusMonths(1).withDayOfMonth(1);
        List<ReplyEntity> replyList = replyRepository.findAllByCreatedAtBetween(lastMonthFirstDay, currentDate);
        List<List<Integer>> rankUsers = memberReplyAggregation.getRankUser(replyList);
        IntStream.range(0, rankUsers.size())
                .forEach(j -> {
                    List<Integer> replyAggregation = rankUsers.get(j);
                    memberReplyService.saveMemberReply(
                            MemberReplyDTO.builder()
                                    .memberId(Long.valueOf(replyAggregation.get(0)))
                                    .replyCount(replyAggregation.get(1))
                                    .recordDate(lastMonthFirstDay.toLocalDate())
                                    .rank(j + 1)
                                    .build(),
                            MemberReplyConverter::toEntity
                    );
                });
    }

//    @Scheduled(fixedRate=1000 * 1000)
//    public void mailTest() throws Exception {
//        mailForNewRelease.Send();
//    }
}
