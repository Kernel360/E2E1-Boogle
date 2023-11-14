package com.kernel360.boogle.statistics.memberinflow.scheduler;

import com.kernel360.boogle.statistics.memberinflow.business.MemberInflowAggregation;
import com.kernel360.boogle.statistics.memberinflow.db.MemberInflowEntity;
import com.kernel360.boogle.statistics.memberinflow.db.MemberInflowRepository;
import com.kernel360.boogle.statistics.memberinflow.model.MemberInflowDTO;
import com.kernel360.boogle.statistics.memberinflow.service.MemberInflowConverter;
import com.kernel360.boogle.statistics.memberinflow.service.MemberInflowService;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@EnableAsync
@RequiredArgsConstructor
public class MemberInflowScheduler {

    private final MemberInflowAggregation memberInflowAggregation;
    private final MemberInflowRepository memberInflowRepository;
    private final MemberInflowService memberInflowService;
    private final MemberInflowConverter memberInflowConverter;

    /**
     * 매주 월요일 새벽 2시에 그 전주 MemberInflow를 업데이트 할 것<br>
     * 가령 2023년 11월 20일 02시에 7일 전인 11월 13일부터 11월19까지의 멤버 유입 총합을 계산하여<br>
     * 11월 3주차로 MEMBER_INFLOW 테이블에 업데이트함
     */
    @Scheduled(cron="0 0 2 * * MON")
    public void scheduledWeeklyMemberInflow() {
        LocalDate updateDate = LocalDate.now().minusWeeks(1);

        List<Integer> monthAndWeekNumber = memberInflowAggregation.getMonthAndWeekNumber(updateDate);

        if(
                memberInflowRepository.findByYearAndMonthAndWeek(
                        updateDate.getYear(),
                        monthAndWeekNumber.get(0),
                        monthAndWeekNumber.get(1)
                ).isEmpty()
        ){
            memberInflowService.saveWeeklyInflow(MemberInflowDTO.builder()
                    .year(updateDate.getYear())
                    .month(monthAndWeekNumber.get(0))
                    .week(monthAndWeekNumber.get(1))
                    .inflow(memberInflowAggregation.getWeeklyInflow(updateDate))
                    .build(), memberInflowConverter::toEntity);
        }
    }
}
