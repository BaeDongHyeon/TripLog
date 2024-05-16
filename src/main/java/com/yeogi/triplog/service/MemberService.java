package com.yeogi.triplog.service;

import com.yeogi.triplog.domain.member.Member;
import com.yeogi.triplog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void signUpMember(Member member) {
        memberRepository.save(member);
    }

    public boolean isExistsEmail(String email) {

        return memberRepository.findByEmail(email) != null;
    }
}
