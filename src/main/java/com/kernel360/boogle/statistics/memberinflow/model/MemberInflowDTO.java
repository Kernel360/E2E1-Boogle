package com.kernel360.boogle.statistics.memberinflow.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class MemberInflowDTO {
    private Integer year;
    private Integer month;
    private Integer week;
    private Integer inflow;
}
