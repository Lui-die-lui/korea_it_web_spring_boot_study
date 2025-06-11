package com.koreait.spring_boot_study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStudyApplication.class, args);
	}
}
// M (model)
// 데이터 및 비즈니스 로직 처리 - Entity, Service, Repository

// V (view)
// 사용자에게 보여지는 화면 - html, Json, 응답 등등 반환값들

// C (coltroller)
// 요청을 받아서 모델에 전달하고, 처리결과를 뷰로 반환 - RestController, Controller
// entity
// db colum, 내가 가진 자바 객체가 1:1 관계가됨


