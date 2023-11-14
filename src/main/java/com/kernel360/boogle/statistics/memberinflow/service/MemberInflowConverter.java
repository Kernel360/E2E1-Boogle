package com.kernel360.boogle.statistics.memberinflow.service;

import com.kernel360.boogle.statistics.memberinflow.db.MemberInflowEntity;
import com.kernel360.boogle.statistics.memberinflow.model.MemberInflowDTO;
import org.springframework.stereotype.Service;

@Service
public class MemberInflowConverter {
    public MemberInflowDTO toDto(MemberInflowEntity memberInflowEntity) {
        return MemberInflowDTO.builder()
                .year(memberInflowEntity.getYear())
                .month(memberInflowEntity.getMonth())
                .week(memberInflowEntity.getWeek())
                .inflow(memberInflowEntity.getInflow())
                .build();
    }

    public MemberInflowEntity toEntity(MemberInflowDTO memberInflowDTO) {
        return MemberInflowEntity.builder()
                .year(memberInflowDTO.getYear())
                .month(memberInflowDTO.getMonth())
                .week(memberInflowDTO.getWeek())
                .inflow(memberInflowDTO.getInflow())
                .build();
    }
}
