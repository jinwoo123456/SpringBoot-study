package com.edu.springboot;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.springboot.jdbc.IMemberService;
import com.edu.springboot.jdbc.MemberDTO;

import jakarta.servlet.http.HttpServletRequest;
import oracle.jdbc.proxy.annotation.Post;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {
	
	/*
	 * 서비스 인터페이스를 통해 Mapper의 메서드를 호출하므로 여기서
	 * 자동주입 받아서 준비한다. 해당 인터페이스는 @Mapper 어노테이션이
	 * 부착되어 있으므로 컨테이너가 시작될 때 자동으로 빈이 생성된다.
	 * */
	@Autowired
	IMemberService dao;
	

	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	//회원목록
	@RequestMapping("list.do")
	public String member2(Model model, MemberDTO memberDTO ) {
		/*
		 dato.select() : 매퍼 인터페이스의 추상메서드를 호출한다.
		 그러면 인터페이스와 연결된 Mapper 파일에 정의된 특정 엘리먼트가
		 호출되오 실행되고 결과를 반환한다.*/
		
		//검색기능 없음
		//model.addAttribute("memberList",dao.select());
		//검색기능 추가 
		model.addAttribute("memberList",dao.select(memberDTO));
		return "list";
	}
	
	//글쓰기 진입
	@GetMapping("/regist.do")
	public String member1() {
		return "regist";
	}
	
	//글쓰기 처리
//	@PostMapping("/regist.do")
//	public String member6(MemberDTO memberDTO) {
//		//수정할 내용을 DTO로 받은 후 전달
//		int result = dao.insert(memberDTO);
//		//update된 결과는 1이면 성공, 0이면 실패
//		if(result==1) System.out.println("입력되었습니다.");
//		return "redirect:list.do";
//	}
	@PostMapping("/regist.do")
	public String member6(HttpServletRequest req) {
		/*
		 * 전송된 폼값은 request 내장객체를 통해 전달받는다.
		 * 이 경우 폼값은 개별적으로 받아야 한다.
		 * */
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		String name = req.getParameter("name");
		
		//함수 호출시 개별적으로 전달한다. 
		int result = dao.insert(id,pass,name);
		if(result==1) System.out.println("입력되었습니다.");
		
		return "redirect:list.do";
	}
	
	//회원 수정
	//수정 1: 기존 회원정보를 수정페이지에 설정
//	@GetMapping("/edit.do")
//	public String member3(MemberDTO memberDTO , Model model) {
//		//파라미터로 전달된 id를 DTO로 받은 후 전달
//		memberDTO = dao.selectOne(memberDTO);
//		//매퍼에서 인출한 회원정보를 View로 전달
//		model.addAttribute("dto",memberDTO);
//		return "edit";
//		
//	}
//	@PostMapping("/edit.do")
//	public String member7(MemberDTO memberDTO) {
//		int result = dao.update(memberDTO);
//		if(result==1) System.out.println("수정되었습니다.");
//		return "redirect:list.do";
//	}
	
	@GetMapping("/edit.do")
	public String member3(HttpServletRequest req, MemberDTO memberDTO , Model model) {
		//request 내장객체로 id를 받는다.
		memberDTO = dao.selectOne(req.getParameter("id"));
		//매퍼에서 인출한 회원정보를 View로 전달
		model.addAttribute("dto",memberDTO);
		return "edit";
		
	}
	@PostMapping("/edit.do")
	public String member7(HttpServletRequest req) {
		//request 내장객체를 통해 폼값을 받는다.
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		String name = req.getParameter("name");
		//받은 폼값을 Map에 저장한다.
		Map<String, String> paramMap = new HashMap<String,String>();
		paramMap.put("m_id", id);
		paramMap.put("m_pass", pass);
		paramMap.put("m_name", name);
		
		//함수 호출시 전달한다.
		int result = dao.update(paramMap);
		if(result==1) System.out.println("수정되었습니다.");
		return "redirect:list.do";
	}
	
	//회원 삭제
	@RequestMapping("/delete.do")
	public String member4(HttpServletRequest req) {
		String id = req.getParameter("id");
		int result = dao.delete(id);
		if(result==1) System.out.println("삭제되었습니다.");
		return "redirect:list.do";
	}
	
}
