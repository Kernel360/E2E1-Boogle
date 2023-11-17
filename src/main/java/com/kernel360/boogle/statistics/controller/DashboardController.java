package com.kernel360.boogle.statistics.controller;

import com.kernel360.boogle.statistics.memberinflow.business.MemberInflowAggregation;
import com.kernel360.boogle.statistics.memberreply.business.MemberReplyProcess;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class DashboardController {
    private final MemberInflowAggregation memberInflowAggregation;
    private final MemberReplyProcess memberReplyProcess;

    @GetMapping("/admin/dashboard")
    public ModelAndView dashboard() {
        ModelAndView mv = new ModelAndView("statistics/admin/statistics-admin-dashboard");
        mv.addObject("weekly_local_date_time", memberInflowAggregation.getWeeklyLocalDateTime());
        mv.addObject("weekly_inflow", memberInflowAggregation.getWeeklyInflow());
        mv.addObject("monthly_local_date", memberInflowAggregation.getMonthlyLocalDate());
        mv.addObject("monthly_inflow",memberInflowAggregation.getMonthlyInflow());

        mv.addObject("monthly_hot_members", memberReplyProcess.getMemberListMap());
        mv.addObject("last_five_month_reply", memberReplyProcess.getReplyCountForFiveMonthList());
        return mv;
    }
}
