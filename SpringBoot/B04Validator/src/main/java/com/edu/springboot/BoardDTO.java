package com.edu.springboot;

import lombok.Data;

/*
 * 멤버변수에 대한 getter,setter, toString()과
 * Set컬렉션에서 중복 제거를 위한 hashCode(), equals()
 * 그리고 기본 생성자까지 자동생성된다.
 * */
@Data
public class BoardDTO {

	private int idx;
	private String userid;
	private int title;
	private String content;
	
	
}
