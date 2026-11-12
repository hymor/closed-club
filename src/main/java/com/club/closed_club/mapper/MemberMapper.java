package com.club.closed_club.mapper;

import com.club.closed_club.dto.MemberResponse;
import com.club.closed_club.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    public MemberResponse toResponse(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getStatus()
        );
    }

}
