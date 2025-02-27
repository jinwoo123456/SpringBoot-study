package com.edu.springboot.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * DAO 역할의 인터페이스 정의
 * 타입매겨변수는 테이블로 사용할 Member와 기본키로 설정한 필드의 타입을
 * 명시한다. long은 기본자료형이므로 Wrapper클래스인 Long으로 작성해야한다.
 * */
@Repository
public interface MemberRepository extends 
JpaRepository<Member, Long>{
	
	/*
	 * 반환타입을 List로 설정하면 엔티티에서 인출한 레코드만 반환한다.
	 * 즉 해당 페이지에서 출력할 ResultSet만 가져오게 된다.
	 * */
	//List<Member> findByNameLike(String keyword,Pageable pageable);
	
	/*
	 * 반환타입을 page로 설정하면 인출된 ResultSet을 포함하여 페이징에 관련된
	 * 다양한 정보를 반환한다. 총 페이지 수,레코드 갯수 등이 포함된다.*/
	Page<Member> findByNameLike(String keyword,Pageable pageable);
	
}
