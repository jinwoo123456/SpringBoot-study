package com.edu.springboot.jpa;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data : 게터, 세터, 기본생성자,toString 등을 추가 
@Data
//@AllArgsConstructor : 인자생성자 
@AllArgsConstructor 
//기본 생성자를 PROTECTED로 선언.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
/*
 * 빌더패턴을 적용하여 메서드 체이닝을 통해 멤버변수를 초기화할 수 있다.
 * 디자인 패턴 중 하나로 싱글톤과 같이 학습해 보도록 한다.*/
@Builder
@Entity(name = "JPAMEMBER01")
public class Member {
	
	//PK, 시퀀스 설정
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	//name속성에 지정한 이름으로 컬럼 생성
	@Column(name = "create_date")
	private LocalDate createDate;
}
	
