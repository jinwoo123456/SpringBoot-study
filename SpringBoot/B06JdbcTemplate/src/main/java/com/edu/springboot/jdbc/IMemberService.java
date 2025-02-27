package com.edu.springboot.jdbc;

import java.util.List;

import org.springframework.stereotype.Service;

/*
 * 컨트롤러와 DAO 사이에 매개 역할을 하는 인터페이스로 DAO 클래스의 부모
 * 역할을 한다.
 * @Service 어노테이션은 @Controller와 동일하게 스프링 컨테이너가 시작될 때
 * 자동으로 빈이 생성된다. 따라서 이 클래스도 기본 패키지 하위에 있어야 한다.
 * */
@Service
public interface IMemberService {
	//회원정보 추가
	public List<MemberDTO> select();
	//회원목록(리스트)
	public int insert(MemberDTO memberDTO);
	//정보 조회
	public MemberDTO selectOne(MemberDTO memberDTO);
	//정보 수정
	public int update(MemberDTO memberDTO);
	// 삭제(탈퇴)
	public int delete(MemberDTO memberDTO);
	
}
