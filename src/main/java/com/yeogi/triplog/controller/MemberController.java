package com.yeogi.triplog.controller;

import com.yeogi.triplog.domain.member.Member;
import com.yeogi.triplog.domain.member.form.MyPageMemberForm;
import com.yeogi.triplog.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{email}")
    public String viewMyPage(@PathVariable("email") String email, Model model) {

        MyPageMemberForm myPageMemberForm = memberService.getMyInfo(email);
        log.info("myInfoEmail:{}", myPageMemberForm.getEmail());

        model.addAttribute("myPageMemberForm", myPageMemberForm);

        return "/member/myPage";
    }

    @GetMapping("/{email}/edit")
    public String editMyPage(@PathVariable("email") String email, Model model) {
        MyPageMemberForm myPageMemberForm = memberService.getMyInfo(email);
        model.addAttribute("myPageMemberForm", myPageMemberForm);
        return "/member/editMyPage";
    }

    @PostMapping("/{email}/edit")
    public String editMyPage(@Valid @ModelAttribute MyPageMemberForm myPageMemberForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/member/editMyPage";
        }

        log.info("update success log");

        Member updateMember = memberService.updateMyInfo(myPageMemberForm);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(
                updateMember,
                authentication.getCredentials(),
                authentication.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(newAuth);

        return "redirect:/mypage/" + myPageMemberForm.getEmail();
    }
}
