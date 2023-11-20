package com.kernel360.boogle.statistics.memberreply.business;

import com.kernel360.boogle.member.model.MemberDataDTO;
import com.kernel360.boogle.member.service.MemberService;
import com.kernel360.boogle.statistics.memberreply.model.MemberReplyDTO;
import com.kernel360.boogle.statistics.memberreply.service.MemberReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MemberReplyProcess {

    private final MemberReplyService memberReplyService;

    private final MemberService memberService;

    public List<List<Integer>> getProcessedReplyList() {
        LocalDate lastMonthFirstDay = LocalDate.now().minusMonths(1).withDayOfMonth(1);
        List<MemberReplyDTO> dtoList = memberReplyService.getMemberReplyList(lastMonthFirstDay);

        return dtoList.stream()
                .map(dto -> List.of(dto.getMemberId().intValue(), dto.getReplyCount()))
                .collect(Collectors.toList());
    }

    public List<Integer> getReplyCountForFiveMonthList() {
        List<Integer> result = new ArrayList<>();

        for (int i = 1; i <= 5; i ++) {
            LocalDate lastMonthFirstDay = LocalDate.now().minusMonths(i).withDayOfMonth(1);
            List<MemberReplyDTO> dtoList = memberReplyService.getMemberReplyList(lastMonthFirstDay);

            result.addAll(dtoList.stream()
                    .map(MemberReplyDTO::getReplyCount)
                    .toList());
        }
        return result;
    }


    public List<List<String>> getMemberListMap() {
        List<List<Integer>> processedReplyList = getProcessedReplyList();
        List<List<String>> memberReplyList = new ArrayList<>();

        int rank = 1;
        for (List<Integer> replyList : processedReplyList) {
            int memberId = replyList.get(0);
            int replyCount = replyList.get(1);

            MemberDataDTO memberDataDTO = memberService.findByIdForDashboard(Long.valueOf(memberId));
            List<String> memberInfo = Arrays.asList(
                    String.valueOf(rank),
                    String.valueOf(replyCount),
                    memberDataDTO.getName(),
                    memberDataDTO.getNickname(),
                    memberDataDTO.getEmail()
            );

            memberReplyList.add(memberInfo);
            rank++;
        }

        return memberReplyList;
    }
}
