package com.kernel360.boogle.statistics.memberinflow.controller;

import com.kernel360.boogle.book.service.BookService;
import com.kernel360.boogle.statistics.memberinflow.business.MemberInflowAggregation;
import com.kernel360.boogle.statistics.memberinflow.db.MemberInflowEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberInflowController {
    private final MemberInflowAggregation memberInflowAggregation;

    @GetMapping("/admin/dashboard")
    public ModelAndView dashboard() {
        ModelAndView mv = new ModelAndView("statistics/admin/statistics-admin-dashboard");
        mv.addObject("weekly_local_date_time", memberInflowAggregation.getWeeklyLocalDateTime());
        mv.addObject("weekly_inflow", memberInflowAggregation.getWeeklyInflow());
        return mv;
    }
}
