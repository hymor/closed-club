package com.club.closed_club.controller;

import com.club.closed_club.dto.MemberCreateRequest;
import com.club.closed_club.dto.MemberResponse;
import com.club.closed_club.dto.MemberUpdateRequest;
import com.club.closed_club.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;


    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<MemberResponse> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public MemberResponse getMemberById(@PathVariable Integer id) {
        return memberService.getMemberById(id);
    }

    @PostMapping
    public MemberResponse createMember(@Valid @RequestBody MemberCreateRequest request) {
        return memberService.createMember(request);
    }

    @PutMapping("/{id}")
    public MemberResponse updateMember(@PathVariable Integer id, @Valid @RequestBody MemberUpdateRequest request) {
        return memberService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Integer id) {
        memberService.deleteMember(id);

        return ResponseEntity.noContent().build();
    }
}
