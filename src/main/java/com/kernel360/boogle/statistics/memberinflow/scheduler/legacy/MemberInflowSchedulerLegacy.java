package com.kernel360.boogle.statistics.memberinflow.scheduler.legacy;

import com.kernel360.boogle.statistics.memberinflow.business.MemberInflowAggregation;
import com.kernel360.boogle.statistics.memberinflow.db.MemberInflowEntity;
import com.kernel360.boogle.statistics.memberinflow.db.MemberInflowRepository;
import com.kernel360.boogle.statistics.memberinflow.service.MemberInflowService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class MemberInflowSchedulerLegacy {

    private final MemberInflowAggregation memberInflowAggregation;
    private final MemberInflowRepository memberInflowRepository;
    private final MemberInflowService memberInflowService;

    /**
     * 해당 레거시 코드는 회원 유입을 월별로 계산하고 DB에 초기화하기 위해 사용했던 코드입니다.<br>
     * 만약 MEMBER_INFLOW 데이터가 삭제되고 백업 데이터도 없을때 회원 데이터는 남아있다면 아래 코드를 사용하여 다시 복원할 수 있습니다.
     * */
    // @Scheduled(fixedDelay = 30 * 1000)
    public void scheduledMemberInflow() {
        List<List<Object>> inflowTimeline = memberInflowAggregation.getWeeklyTimeline(Optional.empty());
        for (List<Object> inflow : inflowTimeline) {
            if(
                    memberInflowRepository.findByYearAndMonthAndWeek(
                            (int)inflow.get(0),
                            (int)inflow.get(1),
                            (int)inflow.get(2)
                    ).isEmpty()
            ){
                memberInflowRepository.save(MemberInflowEntity.builder()
                        .year((int)inflow.get(0))
                        .month((int)inflow.get(1))
                        .week((int)inflow.get(2))
                        .inflow(memberInflowAggregation.getSingleWeeklyInflow(LocalDate.parse((String)inflow.get(3))))
                        .build());
            }
        }
    }

}
