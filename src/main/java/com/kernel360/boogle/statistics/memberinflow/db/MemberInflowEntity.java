package com.kernel360.boogle.statistics.memberinflow.db;

import lombok.*;

import javax.persistence.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "MEMBER_INFLOW")
public class MemberInflowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "month", nullable = false)
    private Integer month;

    @Column(name = "week", nullable = false)
    private Integer week;

    @Column(name = "inflow", nullable = false)
    private Integer inflow;
}
