package com.yeogi.triplog.service;

import com.yeogi.triplog.domain.member.form.EmailCheckRequest;
import com.yeogi.triplog.domain.member.form.MemberSignUpForm;
import com.yeogi.triplog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void signUpMember(MemberSignUpForm memberSignUpForm) {
        memberRepository.save(memberSignUpForm.toEntity());
    }

    public boolean isExistsEmail(EmailCheckRequest emailCheckRequest) {
        String email = emailCheckRequest.getEmail();
        log.info("email={}", email);

        return memberRepository.findByEmail(email) != null;
    }
}
