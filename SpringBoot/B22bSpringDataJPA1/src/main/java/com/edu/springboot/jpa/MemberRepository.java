package com.edu.springboot.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * DAO 역할의 인터페이스.
 * : 타입 매개변수는 테이블(엔티티)로 사용할 Member와 기본키로 사용할 필드의
 * 타입을 명시한다. long은 기본자료형이므로 Wrapper클래스인 Long으로
 * 작성해야한다.
 * */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{

	//제네릭 타입의 매개변수 : long이 아니라 Long로작성
	//기본적인 Create,Read,Update,Delect 자동으로 생성
}
