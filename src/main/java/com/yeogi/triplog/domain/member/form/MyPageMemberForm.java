package com.yeogi.triplog.domain.member.form;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyPageMemberForm {

    private String email;

    private String name;

    private String nickname;

    private String phone;
}
