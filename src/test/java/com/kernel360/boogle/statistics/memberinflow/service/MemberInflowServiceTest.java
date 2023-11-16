package com.kernel360.boogle.statistics.memberinflow.service;

import com.kernel360.boogle.bookreport.service.BookReportService;
import com.kernel360.boogle.statistics.memberinflow.business.MemberInflowAggregation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@ActiveProfiles("test")
class MemberInflowServiceTest {

    @Autowired
    private MemberInflowAggregation memberInflowAggregation;
    @Test
    @DisplayName("2023년 11월 셋째주 사용자 유입수 일치 테스트 (13일 자정 ~ 19일 자정 169명)")
    void getWeeklyInflowTest() {
        assertEquals(
                0,
                memberInflowAggregation.getWeeklyInflow(LocalDate.of(2023, 11, 13))
        );
    }
}