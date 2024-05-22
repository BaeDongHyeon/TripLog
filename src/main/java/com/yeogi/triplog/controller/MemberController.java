package com.yeogi.triplog.controller;

import com.yeogi.triplog.domain.member.form.MemberSignUpForm;
import com.yeogi.triplog.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public String signUpMember(@Valid @ModelAttribute MemberSignUpForm memberSignUpForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "member/signUpPage";
        }

        memberService.signUpMember(memberSignUpForm);
        return "redirect:/";
    }

    @PostMapping("/email/duplicate/check")
    @ResponseBody
    public ResponseEntity<Boolean> emailDuplicateCheck(@RequestBody Map<String, String> request) {
        log.info("email={}", request.get("email"));
        return ResponseEntity.ok(memberService.isExistsEmail(request.get("email")));
    }
}
