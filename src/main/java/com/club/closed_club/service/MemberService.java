package com.club.closed_club.service;

import com.club.closed_club.dto.MemberCreateRequest;
import com.club.closed_club.dto.MemberResponse;
import com.club.closed_club.dto.MemberUpdateRequest;
import com.club.closed_club.entity.Member;
import com.club.closed_club.exception.MemberNotFoundException;
import com.club.closed_club.repository.MemberRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;


    private MemberResponse toResponse(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getStatus()
        );
    }

    public List<MemberResponse> getAllMembers() {
        return memberRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public MemberResponse getUserById(@PathVariable Integer id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
        return toResponse(member);

    }

    public MemberResponse createMember(MemberCreateRequest request) {
        Member member = new Member(request.name(), true);
        Member savedMember = memberRepository.save(member);
        return toResponse(savedMember);
    }

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberResponse update(Integer id, MemberUpdateRequest updateMember) {
        Member existing = memberRepository.findById(id)
                .orElseThrow(() ->  new MemberNotFoundException(id));
       existing.setName(updateMember.name());
       existing.setStatus(updateMember.status());

       Member updatedMember = memberRepository.save(existing);
       return toResponse(updatedMember);
    }

    @Transactional
    public void deleteMember(Integer id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
        memberRepository.delete(member);
    }
}
