package com.kernel360.boogle.member.controller;

import com.kernel360.boogle.member.db.MemberEntity;
import com.kernel360.boogle.member.model.MemberSignupDTO;
import com.kernel360.boogle.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
class SignupControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private MemberService memberService;

    @BeforeEach
    public void setUp(@Autowired WebApplicationContext applicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .alwaysDo(print())
                .build();
    }

    @Test
    void signup() throws Exception {
        MemberEntity member = memberService.signup(
                MemberSignupDTO.builder()
                        .email("kkk@gmail.com")
                        .password("password")
                        .name("김커널")
                        .nickname("커널킴")
                        .gender("M")
                        .birthdate(LocalDate.of(2000, 1, 1))
                        .phoneNumber("010-1111-2222")
                        .build());

        MultiValueMap<String, String> mvMap = new LinkedMultiValueMap<>();
        mvMap.add("email", "kkk@gmail.com");
        mvMap.add("password", "password");
        mvMap.add("name", "김커널");
        mvMap.add("nickname", "커널킴");
        mvMap.add("gender", "M");
        mvMap.add("birthdate", "2000-01-01");
        mvMap.add("phoneNumber", "010-1111-2222");

        mockMvc.perform(
                post("/signup").with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .params(mvMap)
        ).andExpect(redirectedUrl("login")).andExpect(status().is3xxRedirection());
    }
}