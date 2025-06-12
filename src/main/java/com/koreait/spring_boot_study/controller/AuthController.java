package com.koreait.spring_boot_study.controller;

import com.koreait.spring_boot_study.dto.SigninReqDto;
import com.koreait.spring_boot_study.dto.SignupReqDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
//    @RequestParam
    // 클라이언트가 URL 쿼리스트링으로 넘긴 값을 메소드 파라미터로 전달

    @GetMapping("/get")
    public String getUser(@RequestParam String userId) {
        System.out.println("RequestParam으로 들어온 값 : " + userId);
        return "RequestParam으로 들어온 값 : " + userId;

        // 사용법
        // ?+(userId)=넣을 값(10)

//        http://localhost:8080/auth/get?userId=10
//        RequestParam으로 들어온 값 : 10 출력됨
    }
    @GetMapping("/get/name")
    public String getUsername(@RequestParam(value = "name", defaultValue ="홍길동") String username, @RequestParam(required = false) Integer age) {
        System.out.println(username + age);
        return username + age;
    }

        // http://localhost:8080/auth/get/name?name=lsg&age=30
        // /name?name 이건 /name?username 과 같음
        // @RequestParam(value = "name") 이렇게 해주면 됨

        // @RequestParam(required = false) 기본값은 true,
        // false해놓으면 받아도 되고 안받아도 됨

        // int = null 불허
        // Integer = null 허용
        // -> 정보를 받을땐 Integer로 받는게 맞음
        // 가장 좋은 방법은 숫자여도 String
        // 나중에 parseInt 로 숫자 변환 하기
        //  defaultValue ="홍길동" - 값 안넣으면 일케 뜸

        // 값 두개 보내고싶으면 &

        // ===============================================================================

        // *선생님 설명*
        // 안에서 사용하는 변수명과 커리 스트링의 키값이 다를 경우, 괄호안에 표기해주면 됨.
        // and 기본값도 설정이 가능함
        // and 다른 타입도 가능하며 여러개의 RequestParam도 받을 수 있다.
        // int는 null을 허용하지 않기 때문에 값이 없음의 상태
        // 그래서, required = false를 했지만 에러가 듬 => Integer로 해야 null로 받을 수 있다.
        // 만약 필수값이 false이고 기본값이 설정되어있다면 필수값 설정이 무의미

        @GetMapping("/Get/names")
        public String getUsernames(@RequestParam List<String> names) {
            return names.toString();
        }

        // RequestParam 주의사항
        // 파라미터가 없으면 500에러
        // 타입이 안맞을때
        // 이름이 불일치
        // 민감한 정보 금지

        // 요청주소는 /search -> name, email
        // name은 필수x, email은 기본값으로 no-email
        // 요청 -> /auth/search?name=lee
        // 반환으로 -> "검색 조건 - 이름 : --- , 이메일 : --- "


            @GetMapping("/search")
                public String getEmail(@RequestParam(required = false)String name,@RequestParam(defaultValue = "no-email") String email) {
//                System.out.println("검색조건 - 이름: " + name +"," +"이메일: "+email);
                return "검색조건 - 이름: " + name +"," +"이메일: "+email;
            }

    // @RequestBody
    // HTTP 요청의 바디에 들어있는 JSON 데이터를 자바 객체(DTO)로 변환해서 주입해주는 어노테이션
    // 클라이언트가 JSON 형식으로 데이터 보냄
    // 백엔드 서버는 그 JSON을 @RequestBody가 붙은 DTO로 자동 매핑
    // 일반적으로 POST, PUT, PATCH에서 사용

    // DTO(Data Transfer Object)
    // 데이터를 전달하기 위한 객체
    // 클라이언트간에 데이터를 주고받을 때 사용하는 중간 객체


    @PostMapping("/signup")
    public String signup(@RequestBody SignupReqDto signupReqDto) {
        System.out.println(signupReqDto);
        return signupReqDto.getUsername() + "님 회원가입이 완료되었습니다.";
    }

    // Post 요청 signin 로그인 로직
    // SigninReqDto -> email, password
    // 반환 "로그인 완료 : " + signinReqDto.getEmail() + "님 반갑습니다."

    @PostMapping("/signin")
    public String signin(@RequestBody SigninReqDto signinReqDto) {
        System.out.println(signinReqDto);
        return "로그인 완료 : " + signinReqDto.getEmail() + "님 반갑습니다.";
    }



}
