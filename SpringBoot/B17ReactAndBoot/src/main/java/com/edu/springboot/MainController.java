package com.edu.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@RequestMapping("/notview")
	@ResponseBody
	public String main() {
		return "View 없이 컨트롤러에서 즉시 출력";
	}
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	/* RestController에서 제공하는 JSON데이터를 받아서 출력만 하면 되므로
	여기서는 View에 대한 매핑만 하면된다. */
	@GetMapping("/boardList.do")
	public String BoardList(){
		return "boardList";
	}
	@GetMapping("/boardView.do")
	public String boardView(){
		return "boardView";
	}
	
	@GetMapping("/ajaxBoardList.do")
	public String ajaxBoardList(){
		return "ajaxBoardList";
	}	
	@GetMapping("/ajaxBoardView.do")
	public String AjaxBoardView(){
		return "ajaxBoardView";
	}
}
 