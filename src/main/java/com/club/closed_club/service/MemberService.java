package com.club.closed_club.service;

import com.club.closed_club.dto.MemberCreateRequest;
import com.club.closed_club.dto.MemberResponse;
import com.club.closed_club.dto.MemberUpdateRequest;
import com.club.closed_club.entity.Member;
import com.club.closed_club.exception.MemberNotFoundException;
import com.club.closed_club.mapper.MemberMapper;
import com.club.closed_club.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public List<MemberResponse> getAllMembers() {
        return memberRepository.findAll()
                .stream()
                .map(memberMapper::toResponse)
                .toList();
    }

    public MemberResponse getMemberById(Integer id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
        return memberMapper.toResponse(member);

    }

    public MemberResponse createMember(MemberCreateRequest request) {
        Member member = new Member(request.name(), true);
        Member savedMember = memberRepository.save(member);
        return memberMapper.toResponse(savedMember);
    }

    public MemberService(MemberRepository memberRepository,
                         MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    public MemberResponse update(Integer id,
                                 MemberUpdateRequest updateMember) {
        Member existing = memberRepository.findById(id)
                .orElseThrow(() ->  new MemberNotFoundException(id));
       existing.setName(updateMember.name());
       existing.setStatus(updateMember.status());

       Member updatedMember = memberRepository.save(existing);

       return memberMapper.toResponse(updatedMember);
    }

    @Transactional
    public void deleteMember(Integer id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));

        memberRepository.delete(member);
    }
}
