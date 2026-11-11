package com.club.closed_club.repository;

import com.club.closed_club.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {

}
