package com.edu.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {

	
	@RequestMapping("/")
	public String home() {
		return "home";
	}

	@RequestMapping("write.do")
	public String insert1() {
		return "write";
	}
	/*
	 * Validator 인터페이스를 통한 폼값의 유효성 검증
	 * Spring에서 사용하는 커맨드객체는 전송된 폼값을 한꺼번에 받아 Model에
	 * 저장해준다. 만약 저장되는 속성명을 변경하고 싶다면 @ModelAttribute
	 * 어노테이션을 사용하면된다. 이 코드의 경우 boardDto가 아닌 dto라는
	 * 이름으로 Model에 저장된다.
	 * */
	@RequestMapping("/writeAction1.do")
	public String writeAction1(
			@ModelAttribute("dto") BoardDTO boardDTO,
			BindingResult result) {
		
		//폼값 검증에 성공한 경우 포워드할 View의 경로 설정
		String page = "result";
		System.out.println(boardDTO);
		
		//폼값 검증을 위한 인스턴스 생성
		BoardValidator validator = new BoardValidator();
		/*
		 * 폼값을 저장한 DTO및 검증결과 전달을 위한 객체를 인수로 전달한다.
		 * 여기서 validate()를 호출하여 검증을 진행하게된다.
		 * */
		validator.validate(boardDTO,result);
		
		//폼값 검증에 실패한 경우 if문의 블럭이 실행됨.
		if(result.hasErrors()) {
			//실패한 경우 재입력을 받기 위해 쓰기페이지로 포워드한다.
			page = "write";
			//폼값 검증에 대한 전체 내용을 출력
			System.out.println("검증 실패 반환값1 : " + result.toString());
			
			//제목 검증에 실패한 경우 개별 메세지 출력
			if(result.getFieldError("title")!=null) {
				System.out.println("제목 검증1(에러코드):"
						+result.getFieldError("title").getCode());
			}
			//내용 검증에 실패한 경우 개별 메세지 출력
			if(result.getFieldError("content")!=null) {
				System.out.println("내용 검증1(디폴트메세지):"
						+result.getFieldError("title").getDefaultMessage());
			}
		}
		
		return page;
		
	}
	/*
	 * 어노테이션을 통한 검증이므로 폼값 저장을 위해 VO 객체에 @Validated
	 * 를 추가해야 한다.
	 * */
	@RequestMapping("/writeAction2.do")
	public String writeAction2(
			@Validated BoardVO boardVO,
			BindingResult result) {
		
		//폼값 검증에 성공한 경우 포워드할 View의 경로 설정
		String page = "result";
		System.out.println(boardVO);
		
		
		//검증을 위한 클래스를 별도로 정의할 필요가 없으므로 주석처리한다.
		//BoardValidator validator = new BoardValidator();
		//validator.validate(boardDTO, result)
		
		
		//폼값 검증을 위한 인스턴스 생성
		//BoardValidator validator = new BoardValidator();
		/*
		 * 폼값을 저장한 DTO및 검증결과 전달을 위한 객체를 인수로 전달한다.
		 * 여기서 validate()를 호출하여 검증을 진행하게된다.
		 * */
		//validator.validate(boardVO,result);
		
		//폼값 검증에 실패한 경우 if문의 블럭이 실행됨.
		if(result.hasErrors()) {
			//실패한 경우 재입력을 받기 위해 쓰기페이지로 포워드한다.
			page = "write";
			//폼값 검증에 대한 전체 내용을 출력
			System.out.println("검증 실패 반환값1 : " + result.toString());
			
			//제목 검증에 실패한 경우 개별 메세지 출력
			if(result.getFieldError("title")!=null) {
				System.out.println("제목 검증1(에러코드):"
						+result.getFieldError("title").getCode());
			}
			//내용 검증에 실패한 경우 개별 메세지 출력
			if(result.getFieldError("content")!=null) {
				System.out.println("내용 검증1(디폴트메세지):"
						+result.getFieldError("title").getDefaultMessage());
		
			}
			
		}
		
		return page;
		
	}
}

