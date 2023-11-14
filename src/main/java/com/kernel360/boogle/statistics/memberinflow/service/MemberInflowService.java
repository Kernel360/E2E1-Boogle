package com.kernel360.boogle.statistics.memberinflow.service;

import com.kernel360.boogle.member.db.MemberEntity;
import com.kernel360.boogle.member.db.MemberRepository;
import com.kernel360.boogle.statistics.memberinflow.db.MemberInflowEntity;
import com.kernel360.boogle.statistics.memberinflow.db.MemberInflowRepository;
import com.kernel360.boogle.statistics.memberinflow.model.MemberInflowDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class MemberInflowService {
    private final MemberRepository memberRepository;
    private final MemberInflowRepository memberInflowRepository;

    public MemberInflowEntity saveWeeklyInflow(MemberInflowDTO memberInflowDTO, Function<MemberInflowDTO, MemberInflowEntity> converter) {
        return memberInflowRepository.save(converter.apply(memberInflowDTO));
    }
}
