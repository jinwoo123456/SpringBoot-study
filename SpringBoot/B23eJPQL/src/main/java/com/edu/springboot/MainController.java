package com.edu.springboot;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.springboot.jpa.Member;
import com.edu.springboot.jpa.MemberService;

@Controller
public class MainController{

	@Autowired
	MemberService memberService;

	@GetMapping("/")
	public String home() {
	    return "home";
	}

	@GetMapping("/selectNameLike1.do")
	public String selectMembers1(@RequestParam("name") String pname, Model model) {
	    System.out.println("selectNameLike1: " + pname);
	    
	    
	    String searchName = "%" + pname + "%";
	    List<Member> result = memberService.selectMembers1(searchName);
	    model.addAttribute("members", result);
	    
	    return "member_list";
	}

	@GetMapping("/selectNameLike2.do")
	public String selectMembers2(@RequestParam("name") String pname, Model model) {
	    System.out.println("selectNameLike2: " + pname);
	    
	    // 뒤에만 %가 있으므로 검색어로 시작되는 레코드 검색.
	    String searchName = pname + "%";
	    //id를 내림차순 정렬
	    Sort sort = Sort.by(Sort.Order.asc("id"));
	    //Pageable은 페이지번호를 0부터 시작하므로 , 현제 패에지번호에서 1을 빼야함
	    List<Member> result = memberService.selectMembers2(searchName, sort);
	    
	    model.addAttribute("members", result);
	    
	    return "member_list";
	}

	@GetMapping("/selectNameLike3.do")
	public String selectMembers3(@RequestParam("name") String pname, 
	                             @RequestParam("page") String page,
	                             Model model) {
	    System.out.println("selectMembers3(검색어): " + pname);
	    System.out.println("selectMembers3(페이지): " + page);
	    
	    String name = pname + "%";
	    Sort sort = Sort.by(Sort.Order.desc("id"));
	    int pageNum = Integer.parseInt(page) - 1;
	    //한 페이지에 5개씩 레코드 출력
	    Pageable pageable = PageRequest.ofSize(5).withPage(pageNum).withSort(sort);
	    //JPQL 실행
	    Page<Member> result = memberService.selectMembers3(name, pageable);
	    //인출한 레코드를 얻어옴
	    List<Member> content = result.getContent();
	    //레코드 갯수
	    long totalElements = result.getTotalElements();
	    //전체 페이지 수
	    int totalPages = result.getTotalPages();
	    //페이지 당 레코드 수
	    int size = result.getSize();
	    //현재 페이지 번호.0부터 시작이므로 1 더해줌.
	    int pageNumber = result.getNumber() + 1;
	    //컨텐츠의 갯수
	    int numberOfElements = result.getNumberOfElements();
	    
	    model.addAttribute("members", content);
	    model.addAttribute("totalElements", totalElements);
	    model.addAttribute("totalPages", totalPages);
	    model.addAttribute("size", size);
	    model.addAttribute("pageNumber", pageNumber);
	    model.addAttribute("numberOfElements", numberOfElements);
	    
	    return "member_list";
	}

	@GetMapping("/selectNameLike4.do")
	public String selectMembers4(@RequestParam("name") String pname, Model model) {
	    System.out.println("selectMembers4(검색어): " + pname);
	    
	    String name = "%" + pname + "%";
	    List<Member> result = memberService.selectMembers4(name);
	    model.addAttribute("members", result);
	    
	    return "member_list";
	}

	
	
}
