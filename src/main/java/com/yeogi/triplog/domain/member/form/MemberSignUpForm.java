package com.yeogi.triplog.domain.member.form;

import com.yeogi.triplog.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSignUpForm {

    private String name;

    private String phone;

    private String email;

    private String password;

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .phone(phone)
                .email(email)
                .password(password)
                .build();
    }
}
