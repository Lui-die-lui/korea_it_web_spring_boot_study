package com.koreait.spring_boot_study.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
class  UserDto {
    private int userId;
    private String username;
    private int age;
}

// controller -> 페이지 자체를 반환 (SSR)
// 즉 서버쪽에서 웹페잊를 렌더링해서 반환하는 SSR
@Controller
public class MainController {
    private List<UserDto> users = new ArrayList<>();
    //MVC 중 View 개념
    // 이러한 방식은 정적 웹페이지를 보여주는 것
    // 데이터 즉 동적인 요소가 없는 정적 웹페이지
    @GetMapping("/main")
    public String getMain() {
        return "main.html"; // resources - static 폴더 내 html 생성
    }

    //SSR에 동적을 추가하면 Thymeleaf를 적용하면 된다
    //html 파일은 static->templates에 있어야 한다
    //Thymeleaf
    //서버에서 HTML을 렌더링할때, 자바 데이터를 끼워 넣을 수 잇게 해주는 템플릿 엔진

    @GetMapping("/profile")
    public String getProfile(Model model) {  // Model = org.springframework.ui 로!
        model.addAttribute("username", "<b>Lsg</b>"); // html 'u'text 쓰면 태그 인식
        model.addAttribute("isAdult",false); // unless = 반대 경우
        model.addAttribute("age",30);
        Map<String, String> userList = new HashMap<>();
        userList.put("이동윤","27");
        userList.put("삼동윤","18");
        userList.put("사동윤","44");
        userList.put("오동윤","3");
        model.addAttribute("userList",userList);
        return "profile.html";
    }

    @GetMapping("/search")
    public String getSearch(@RequestParam String keyword, Model model) {
        model.addAttribute("keyword",keyword);
        return "search.html";
    }
    // 회원가입 / 유저리스트 뽑기

    @GetMapping("/signup")
    public String signup() {
        return "signup.html"; // html 안해도 되긴 함
    }

    @PostMapping("/signup") // 메소드가 다르면 요청주소가 다름 - 처리해주는 post
    public String signupSubmit(@RequestParam String name , @RequestParam int age, Model model) {
        UserDto userDto = new UserDto(users.size()+1,name,age);
        users.add(userDto);
        model.addAttribute("message",name+"님, 가입을 환영합니다.");
        return "signup-result";
    }

}
