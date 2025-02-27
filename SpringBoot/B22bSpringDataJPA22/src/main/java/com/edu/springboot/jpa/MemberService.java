package com.edu.springboot.jpa;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public void insert() {
        Member member;

        member = Member.builder().name("이순신").email("test1@test.com").build();
        memberRepository.save(member);

        member = Member.builder().name("강감찬").email("test2@test.com").build();
        memberRepository.save(member);

        member = Member.builder().name("을지문덕").email("test3@test.com").build();
        memberRepository.save(member);

        member = Member.builder().name("계백").email("test4@test.com").build();
        memberRepository.save(member);

        member = Member.builder().name("김유신").email("test5@test.com").build();
        memberRepository.save(member);

        member = Member.builder().name("연개소문").email("test6@test.com").build();
        memberRepository.save(member);

        member = Member.builder().name("양만춘").email("test7@test.com").build();
        memberRepository.save(member);

        member = Member.builder().name("김종서").email("test8@test.com").build();
        memberRepository.save(member);

        member = Member.builder().name("최영").email("test9@test.com").build();
        memberRepository.save(member);

        member = Member.builder().name("선덕여왕").email("test10@test.com").build();
        memberRepository.save(member);
    }

    // 전체 조회
    public List<Member> selectAll() {
        return memberRepository.findAll();
    }

    // 아이디로 검색\
    /*반환타입이 Optional<엔티티 빈>인 경우에는 결과가 1개만 인출되어야 한다.
     * 2개 이상 인출되면 예외가 발생된다.*/
    public Optional<Member> selectId(Long search) {
        return memberRepository.findById(search);
    }

    // 이름으로 검색
    public Optional<Member> selectName(String search) {
        return memberRepository.findByName(search);
    }

    // 이메일로 검색
    public Optional<Member> selectEmail(String search) {
        return memberRepository.findByEmail(search);
    }
    //이름을 like로 검색
    
    public List<Member> selectNameLike(String search) {
    	List<Member> member = memberRepository.findByNameLike(search);
        return member;
    }
    //이름을 like로 검색 후 내림차순 정렬
    public List<Member> selectNameLikeNameDesc(String search) {
	    	List<Member> member = memberRepository
	    			.findByNameLikeOrderByNameDesc(search);
        return member;
    }
    //Sort 빈을 통해 정렬
    public List<Member> selectNameLike(String search,Sort sort) {
    	List<Member> member = memberRepository.findByNameLike(search,sort);
        return member;
    }

}
