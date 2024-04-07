package com.yeogi.triplog.repository;

import com.yeogi.triplog.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
