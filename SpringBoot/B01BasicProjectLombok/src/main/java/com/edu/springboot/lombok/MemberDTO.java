package com.edu.springboot.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @Data
 * @Getter
 * 인수 생성자 추가
 * @AllArgsConstructor
 * 기본 생성자 추가
 * @NoArgsConstructor
 * */


@Data
public class MemberDTO {

	private String id;
	private String pass;
	private String name;
	private String regidate;
	
}





