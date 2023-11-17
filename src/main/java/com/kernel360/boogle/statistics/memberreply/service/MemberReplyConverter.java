package com.kernel360.boogle.statistics.memberreply.service;

import com.kernel360.boogle.statistics.memberinflow.db.MemberInflowEntity;
import com.kernel360.boogle.statistics.memberinflow.model.MemberInflowDTO;
import com.kernel360.boogle.statistics.memberreply.db.MemberReplyEntity;
import com.kernel360.boogle.statistics.memberreply.model.MemberReplyDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemberReplyConverter {
    public static MemberReplyEntity toEntity(MemberReplyDTO memberReplyDTO) {
        return MemberReplyEntity.builder()
                .memberId(memberReplyDTO.getMemberId())
                .replyCount(memberReplyDTO.getReplyCount())
                .recordDate(memberReplyDTO.getRecordDate())
                .replyRank(memberReplyDTO.getRank())
                .build();
    }

    // Entity를 DTO로 변환하는 메서드
    public static MemberReplyDTO toDTO(MemberReplyEntity memberReplyEntity) {
        return MemberReplyDTO.builder()
                .memberId(memberReplyEntity.getMemberId())
                .replyCount(memberReplyEntity.getReplyCount())
                .recordDate(memberReplyEntity.getRecordDate())
                .rank(memberReplyEntity.getReplyRank())
                .build();
    }

    // Entity 리스트를 DTO 리스트로 변환하는 메서드
    public List<MemberReplyDTO> toDTOList(List<MemberReplyEntity> memberReplyEntities) {
        return memberReplyEntities.stream()
                .map(MemberReplyConverter::toDTO)
                .collect(Collectors.toList());
    }
}
