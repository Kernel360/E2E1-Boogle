package com.kernel360.boogle.statistics.memberinflow.db;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberInflowRepository extends JpaRepository<MemberInflowEntity, Long> {
    Optional<MemberInflowEntity> findByYearAndMonthAndWeek(int year, int month, int week);
    List<MemberInflowEntity> findAllByYearAndMonth(int year, int month);
    MemberInflowEntity save(MemberInflowEntity memberInflow);
}
