package com.kernel360.boogle.statistics.memberinflow.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class MemberInflowDTO {
    private int year;
    private int month;
    private int week;
    private int inflow;
}
