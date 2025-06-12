package com.koreait.spring_boot_study.service;

import com.koreait.spring_boot_study.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service // 있어야 객체 생성 인식 - 서로 의존(의존성 주입)
public class PostService { // 처리는 service 에서 거의 이루어짐
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    public String getPost() {
        String result = postRepository.getPost();
        return result;
         }
}
