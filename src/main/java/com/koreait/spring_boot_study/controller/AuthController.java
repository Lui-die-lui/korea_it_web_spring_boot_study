package com.koreait.spring_boot_study.controller;

import com.koreait.spring_boot_study.dto.SigninReqDto;
import com.koreait.spring_boot_study.dto.SigninRespDto;
import com.koreait.spring_boot_study.dto.SignupReqDto;
import com.koreait.spring_boot_study.dto.SignupRespDto;
import com.koreait.spring_boot_study.service.AuthService;
import com.koreait.spring_boot_study.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
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


//    @PostMapping("/signup")
//    public String signup(@RequestBody SignupReqDto signupReqDto) {
//        System.out.println(signupReqDto);
//        return signupReqDto.getUsername() + "님 회원가입이 완료되었습니다.";
//    }

    // Post 요청 signin 로그인 로직
    // SigninReqDto -> email, password
    // 반환 "로그인 완료 : " + signinReqDto.getEmail() + "님 반갑습니다."

//    @PostMapping("/signin")
//    public String signin(@RequestBody SigninReqDto signinReqDto) {
//        System.out.println(signinReqDto);
//        return "로그인 완료 : " + signinReqDto.getEmail() + "님 반갑습니다.";
//    }

    // ResponseEntity
    // HTTP 응답 전체를 커스터마이징을 해서 보낼 수 있는 스프링 클래스
    // HTTP 상태코드, 응답바디, 응답헤더까지 모두 포함

    @PostMapping("/signin")
    public ResponseEntity<SigninRespDto> signin(@RequestBody SigninReqDto signinReqDto) {
        if(signinReqDto.getEmail() == null || signinReqDto.getEmail().trim().isEmpty()) { // 아예 null이거나, ""일경우
            SigninRespDto signinRespDto = new SigninRespDto("failed","이메일을 다시 입력해주세요.");
            return ResponseEntity.badRequest().body(signinRespDto);
        } else if (signinReqDto.getPassword() == null || signinReqDto.getPassword().trim().isEmpty()) {
            SigninRespDto signinRespDto = new SigninRespDto("failed","비밀번호를 다시 입력해주세요.");
            return ResponseEntity.badRequest().body(signinRespDto);
        }
        SigninRespDto signinRespDto = new SigninRespDto("success", "로그인 성공");
//        return ResponseEntity.status((HttpStatus.OK).body(signinRespDto)); // body 왜 안됨...?
        return ResponseEntity.ok(signinRespDto); // 요청의 관점에서 모든 값을 ok 해버림
    }

    // 200 OK -> 요청 성공
    // 400 Bad Request -> 잘못된 요청 (ex. 유효성 실패, JSON 파싱 오류)
    // 401 Unauthorized -> 인증 실패 (ex. 로그인 안 됨, 토큰 없음)
    // 403 Forbidden -> 접근 권한 없음(ex. 관리자만 접근 가능)
    // 404 Not Found -> 리소스 없음
    // 409 Conflict -> 중복 등으로 인한 충돌 (ex. 이미 존재하는 이메일)
    // 500 Internal Server Error -> 서버 내부 오류(코드 문제, 예외 등)

    // 200은 정상적으로 됐다, 400은 네가 잘못보냈다, 500은 서버가 터졌다

    @PostMapping("/signup") // 대부분 와일드카드 사용
    public ResponseEntity<SignupRespDto> signup(@RequestBody SignupReqDto signupReqDto) {
    return ResponseEntity.ok().body(authService.signup(signupReqDto));
    }

    // 중복 체크 같은 API(like 응답 요청) 는 대부분 200 OK로 응답하고
    // 응답 본문(JSON)에 "중복 여부" 를 표시합니다.
    // 중복체크는 정상적인 요청에 대한 정상적인 응답이기때문에 200 OK다
    // 이메일이 중복이든 아니든 요청 자체는 정상적으로 처리됐기 때문에
    // 400/409 같은 에러코드를 주지 않는다.
    // 대신 JSON 응답 내부에서 중복됨/가능함 을 구분
    // 그럼 언제 에러 코드(409 Conflict)를 쓰느냐?
    // 그거는 진짜 예외 상황일때
    // 중복된 이메일로 회원가입을 실제로 시도했을때 409

}

