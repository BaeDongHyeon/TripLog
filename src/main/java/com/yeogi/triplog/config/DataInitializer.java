package com.yeogi.triplog.config;

import com.yeogi.triplog.domain.member.Member;
import com.yeogi.triplog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.yeogi.triplog.domain.member.MemberRole.*;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public ApplicationRunner initializer() {
        return args -> {
            String email = "abc@naver.com";
            String rawPassword = "1234";
            String encodedPassword = passwordEncoder.encode(rawPassword);

            if (!memberRepository.findByEmail(email).isPresent()) {
                Member member = Member.builder()
                        .email(email)
                        .password(encodedPassword)
                        .name("홍길동")
                        .nickname("나의닉네임")
                        .phone("01012345678")
                        .build();

                member.updateRole(ADMIN);

                memberRepository.save(member);
            }
        };
    }
}

