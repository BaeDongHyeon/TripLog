package com.yeogi.triplog.service;

import com.yeogi.triplog.domain.member.Member;
import com.yeogi.triplog.domain.member.form.EmailCheckRequest;
import com.yeogi.triplog.domain.member.form.LoginForm;
import com.yeogi.triplog.domain.member.form.MemberSignUpForm;
import com.yeogi.triplog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    public void signUpMember(MemberSignUpForm memberSignUpForm) {

        String encodedPassword = passwordEncoder.encode(memberSignUpForm.getPassword());
        memberSignUpForm.setPassword(encodedPassword);

        memberRepository.save(memberSignUpForm.toEntity());
    }

    public boolean isExistsEmail(EmailCheckRequest emailCheckRequest) {

        String email = emailCheckRequest.getEmail();
        log.info("email={}", email);

        Optional<Member> findMember = memberRepository.findByEmail(email);

        return !findMember.isEmpty();
    }

    public void loginMember(LoginForm loginForm) {

        Optional<Member> findMember = memberRepository.findByEmail(loginForm.getEmail());

        if (findMember.isEmpty()) {
            log.info("이메일이 존재하지 않습니다.");
            return;
        }

        Member member = findMember.get();
        if (passwordEncoder.matches(loginForm.getPassword(), member.getPassword())) {
            log.info("로그인 성공");
            return;
        }

        log.info("비밀번호 인증 실패");
    }
}
