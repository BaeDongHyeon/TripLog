package com.yeogi.triplog.controller;

import com.yeogi.triplog.domain.member.form.MemberSignUpForm;
import com.yeogi.triplog.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String signUpPage(@ModelAttribute MemberSignUpForm memberSignUpForm) {
        return "member/signUpPage";
    }

    @PostMapping("/signup")
    public String signUpMember(@ModelAttribute MemberSignUpForm memberSignUpForm) {
        memberService.signUpMember(memberSignUpForm.toEntity());
        return "redirect:/";
    }
}
