package com.kernel360.boogle.statistics.memberinflow.business;

import com.kernel360.boogle.member.db.MemberEntity;
import com.kernel360.boogle.member.db.MemberRepository;
import com.kernel360.boogle.statistics.memberinflow.db.MemberInflowRepository;
import com.kernel360.boogle.statistics.memberinflow.service.MemberInflowService;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
* 비즈니스 명세<br>
* 1. 서비스는 2020년 6월 1일(월)에 시작되었다고 상정한다.<br>
* 2. 주차 계산은 ISO-8601에 따라 목요일이 포함된 달의 주차로 계산하고, 날짜는 월요일부터 일요일까지 계산한다.<br>
* 3. Weekly 스케줄러를 통해 매주 월요일마다 계산되어 DB에 저장되어야한다.<br>
* 4. 비즈니스 로직에서는 서비스 시작일시부터 주차별로 DB에 사용자 유입이 이루어지고 있는지 확인하고 그렇지 않다면 업데이트하는 작업을 월 1회 수행한다.
* */

@Service
@Transactional
@RequiredArgsConstructor
public class MemberInflowAggregation{
    private static final LocalDateTime serviceStartTime = LocalDateTime.of(2020, 6, 1, 0, 0, 0);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final MemberRepository memberRepository;
    private final MemberInflowRepository memberInflowRepository;

    public List<List<Object>> getWeeklyTimeline(Optional<LocalDateTime> ldt) {
        return Stream.iterate(ldt.orElse(serviceStartTime), date -> !date.isAfter(LocalDateTime.now().minusDays(7)), date -> date.plusDays(7))
                .map(date -> {
                    List<Object> entry = new ArrayList<>();
                    entry.add(date.getYear());
                    entry.addAll(getMonthAndWeekNumber(date.toLocalDate().plusDays(3)));
                    entry.add(dateFormatter.format(date));
                    return entry;
                })
                .collect(Collectors.toList());
    }

    public List<Integer> getMonthAndWeekNumber(LocalDate date) {
        LocalDate firstThursday = getFirstThursdayOfMonth(date);
        int weekNumber = (date.getDayOfMonth() - firstThursday.getDayOfMonth()) / 7 + 1;

        List<Integer> result = new ArrayList<>();
        result.add(date.getMonthValue());
        result.add(weekNumber);

        return result;
    }

    private LocalDate getFirstThursdayOfMonth(LocalDate date) {
        LocalDate firstDayOfMonth = LocalDate.of(date.getYear(), date.getMonth(), 1);
        while (firstDayOfMonth.getDayOfWeek() != DayOfWeek.THURSDAY) {
            firstDayOfMonth = firstDayOfMonth.plusDays(1);
        }
        return firstDayOfMonth;
    }

    public int getWeeklyInflow(LocalDate localDate) {
        LocalDateTime startDateTime = localDate.atStartOfDay();

        List<MemberEntity> memberList = memberRepository.findAllBySignupDateBetween(
                startDateTime,
                startDateTime.plusWeeks(1)
        );
        return memberList.size();
    }

    public List<Integer> getWeeklyInflow() {
        List<Integer> yList = new ArrayList<>();
        for (List<Object> timeline : getWeeklyTimeline(Optional.empty())) {
            if (memberInflowRepository.findByYearAndMonthAndWeek(
                    (int)timeline.get(0),
                    (int)timeline.get(1),
                    (int)timeline.get(2)
            ).isPresent()) {
                yList.add(memberInflowRepository.findByYearAndMonthAndWeek(
                        (int)timeline.get(0),
                        (int)timeline.get(1),
                        (int)timeline.get(2)
                ).get().getInflow());
            }
        }
        return yList;
    }

    public List<String> getWeeklyLocalDateTime() {
        List<String> xList = new ArrayList<>();
        for (List<Object> timeline : getWeeklyTimeline(Optional.empty())) {
            xList.add(timeline.get(3).toString());
        }
        return xList;
    }
}
