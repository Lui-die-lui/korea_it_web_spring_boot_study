package com.koreait.spring_boot_study.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignupReqDto {
    private String username;
    private String password;
    private String email;

    /*
    {
    "username " : "이슬기",
    "password" : "1q2w3e4r",
    "email" : "lsg05h@naver.com"
     }
*/
    // postman = json으로 넘겨야함 , 값이 일치해야함 - 아니면 null 뜸
    // SignupReqDto(username=이슬기, password=1q2w3e4r, email=lsg05h@naver.com)
}



