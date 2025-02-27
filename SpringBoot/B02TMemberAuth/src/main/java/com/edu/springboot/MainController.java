package com.edu.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {

	
//	@RequestMapping("/")
	@GetMapping("/")
	public String home() {
		return "home";
	}
	//회원가입 페이지 매핑
	@RequestMapping("/memberRegist.do")
	public String memberRegist() {
		//뷰의 경로 반환
		return "member/regist";
	}	
	//회원가입 폼값 처리 매핑
	@RequestMapping("/memberRegistAction.do")
	public String memberRegistAction(MemberDTO memberDTO) {
		//뷰의 경로 반환
		return "member/regist_action";
		
	}
	//로구인 매핑
	@RequestMapping("/memberLogin.do")
	public String memberLogin() {
		return "member/login";
	}	
}
