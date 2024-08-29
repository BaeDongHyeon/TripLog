package com.yeogi.triplog.service;

import com.yeogi.triplog.domain.member.Member;
import com.yeogi.triplog.domain.member.form.EmailCheckRequest;
import com.yeogi.triplog.domain.member.form.MemberSignUpForm;
import com.yeogi.triplog.domain.member.form.MyPageMemberForm;
import com.yeogi.triplog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.yeogi.triplog.domain.member.MemberRole.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    public void signUpMember(MemberSignUpForm memberSignUpForm) {

        String encodedPassword = passwordEncoder.encode(memberSignUpForm.getPassword());
        memberSignUpForm.setPassword(encodedPassword);

        Member member = memberSignUpForm.toEntity();
        member.updateRole(USER);

        memberRepository.save(member);
    }

    public boolean isExistsEmail(EmailCheckRequest emailCheckRequest) {

        String email = emailCheckRequest.getEmail();
        log.info("email={}", email);

        Optional<Member> findMember = memberRepository.findByEmail(email);

        return !findMember.isEmpty();
    }

    public MyPageMemberForm getMyInfo(String email) {
        Optional<Member> findMember = memberRepository.findByEmail(email);

        if (!findMember.isPresent()) {
            throw new IllegalArgumentException("사용자를 찾을 수 없음");
        }
        Member member = findMember.get();

        return MyPageMemberForm.builder()
                .name(member.getName())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phone(member.getPhone())
                .build();
    }

    public void updateMyInfo(MyPageMemberForm myPageMemberForm) {
        Optional<Member> findMember = memberRepository.findByEmail(myPageMemberForm.getEmail());

        if (!findMember.isPresent()) {
            throw new IllegalArgumentException("사용자를 찾을 수 없음");
        }

        Member member = findMember.get();
        member.updateInfo(myPageMemberForm);

        memberRepository.save(member);
    }
}
