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


    @GetMapping("/")
    public String home() {
        return "home";
    }
	@Autowired
	MemberService memberService;

	@GetMapping("/selectByNameLike.do")
	public String selectByNameLike(
	    @RequestParam("name") String pname,
	    @RequestParam("page") String page,
	    Model model) {

	    System.out.println("selectMembers3 (검색어): " + pname);
	    System.out.println("selectMembers3 (페이지): " + page);

	    //와일드카드 %가 뒤에만 있으므로 pname으로 시작하는 문자열 검색
	    String name = pname + "%";
	    //id를 내림차순 정렬
	    Sort sort = Sort.by(Sort.Order.desc("id"));
	    //Pageable에서는 페이지번호가 0부터 시작이므로 -1해준다.
	    int pageNum = Integer.parseInt(page) - 1;

	    Pageable pageable = PageRequest.ofSize(5)
	                                   .withPage(pageNum)
	                                   .withSort(sort);
	    //페이징에 대한 설정과 검색어를 기반으로 쿼리문 실행
	    Page<Member> result = memberService
	    		.selectNameLike(name, pageable);
	    //레코드 List로 인출
	    List<Member> content = result.getContent();
	    //레코드 갯수 
	    long totalElements = result.getTotalElements();
	    //전체 페이지 수 
	    int totalPages = result.getTotalPages();
	    //페이지 당 레코드 수
	    int size = result.getSize();
	    //현재 페이지번호.0부터 시작이므로 1을 더해줌
	    int pageNumber = result.getNumber() + 1;
	    //컨텐츠의 갯수
	    int numberOfElements = result.getNumberOfElements();

	    model.addAttribute("members", content);
	    model.addAttribute("totalElements", totalElements);
	    model.addAttribute("totalPages", totalPages);
	    model.addAttribute("size", size);
	    model.addAttribute("pageNumber", pageNumber);
	    model.addAttribute("numberOfElements", numberOfElements);

	    //View로 포워드
	    return "member_list";
	}
}
