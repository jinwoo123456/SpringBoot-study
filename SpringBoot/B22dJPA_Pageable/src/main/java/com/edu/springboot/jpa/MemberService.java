package com.edu.springboot.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Service
public class MemberService {



	@Autowired
	private MemberRepository memberRepository;
	
	public Page<Member> selectNameLike(String search,
			Pageable pageable){
		Page<Member> member = memberRepository
				.findByNameLike(search, pageable);
		return member;
	}
}
