package com.koreait.spring_boot_study.repository;

import org.springframework.stereotype.Repository;

@Repository
public class PostRepository {
    public String getPost() {
        System.out.println("레퍼지토리까지 요청이 왔다감");
        return "레퍼지토리에서 보낸 어떠한 데이터";
    }



}
