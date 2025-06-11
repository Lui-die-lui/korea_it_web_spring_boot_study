package com.koreait.spring_boot_study.service;

import com.koreait.spring_boot_study.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service // 있어야 객체 생성 인식 - 서로 의존(의존성 주입)
public class PostService { // 처리는 service 에서 거의 이루어짐
    private final PostRepository postRepository;

//    public ..? 갑자기 졸라빨라짐

    public String getPost() {
        return "서비스에서 보낸 어떠한 게시물의 데이터";
         }
}
