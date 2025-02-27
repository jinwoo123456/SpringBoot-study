package com.edu.springboot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.springboot.jdbc.BoardDTO;
import com.edu.springboot.jdbc.IBoardService;
import com.edu.springboot.jdbc.ParameterDTO;
import com.edu.springboot.utils.PagingUtil;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class MainController {
	
	@Autowired
	IBoardService dao;
	
	@Value("#{myprops['my.page']}") //한 페이지당 게시물 수
	private int pageSize; 
	

	@Value("#{myprops['my.blockpage']}") //한 페이지당 게시물 수
	private int blockPage; 
	

	@RequestMapping("/")
	public String home() {
		return "home";}
	
	@RequestMapping("/list.do")
	public String boardList(Model model,HttpServletRequest req,
			ParameterDTO parameterDTO) {
		int totalCount = dao.getTotalCount(parameterDTO);
		 //한 블럭당 페이지 번호 수
		/*목록에 첫 진입시에는 페이지 번호가 없으므로 1로 설정하고,
		 * 파라미터로 전달된 페이지 번호가 있다면 정수로 반환된다.
		 */
		int pageNum = (req.getParameter("pageNum")==null
				|| req.getParameter("pageNum").equals(""))
				? 1: Integer.parseInt(req.getParameter("pageNum"));
		//페이지에 출력할 게시물의 구간을 계싼
		int start = (pageNum-1) * pageSize +1;
		//계산의 결과는 DTO에 저장
		int end = pageNum * pageSize;
		parameterDTO.setStart(start);
		parameterDTO.setEnd(end);
		
		//View에서 게시물의 가상번호 계산을 위한 값을 Map에 저장
		Map<String, Object> maps = new HashMap<String,Object>();
		maps.put("totalCount", totalCount);
		maps.put("pageSize", pageSize);
		maps.put("pageNum", pageNum);
		model.addAttribute("maps",maps);
		
		//DB에서 인출한 게시물의 목록을 Model에 저장.
		ArrayList<BoardDTO> lists = dao.listPage(parameterDTO);
		model.addAttribute("lists",lists);
		
		//게시판 하단에 출력할 페이지 번호를 String으로 저장 후 Model에 저장.
		String pagingImg = 
				PagingUtil.pagingImg(totalCount,pageSize,
						blockPage,pageNum,
						req.getContextPath()+"/list.do?");
		model.addAttribute("pagingImg",pagingImg);
		
		
		
		return "list";
		
				
	}
	@GetMapping("/write.do")
	public String boardWriteGet(Model model) {
		return "write";
	}
	@PostMapping("/write.do")
	public String boardWritePost(Model model, HttpServletRequest req) {
		String name = req.getParameter("name");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		//파라미터를 개별적으로 전달.
		int result = dao.write(name, title, content);
		System.out.println("글쓰기결과"+result);
		return "redirect:list.do";
	}
	
	//열람 (view)
	@RequestMapping("/view.do")
	public String boardView(Model model,BoardDTO boardDTO
			) {
		dao.visitCountPlus(boardDTO);
		//내용 조회를 위해 게시물의 일련번호를 받은 후 DAO로 전달하여 쿼리 실행
		boardDTO = dao.view(boardDTO);
		//내용에 대해서는 줄바꿈 처리 한다.
		boardDTO.setContent(boardDTO.getContent()
				.replace("\r\n","<br/>"));
		//Model객체에 저장한 후 View로 포워드
		model.addAttribute("boardDTO ",boardDTO);
		return "view";
	}
	//수정1 : 기존 내용을 인출해서 수정폼에 설정
	@GetMapping("/edit.do")
	public String boardEditGet(Model model, BoardDTO boardDTO) {
		//열람에서 사용했던 메서드를 그대로 사용
		boardDTO = dao.view(boardDTO);
		model.addAttribute("boardDTO",boardDTO);
//		수정이 완료되면 열람페이지로 이동. 일련번호가 파라미터로 전달됨
		return "edit";
	}
	//수정2 : 사용자가 입력한 내용을 전송하요 update처리
	@PostMapping("/edit.do")
	public String boardEditPost(BoardDTO boardDTO) {
		int result = dao.edit(boardDTO);
		System.out.println("글 수정 결과: "+ result);
		return "redirect:view.do?idx" + boardDTO.getIdx();
	}
	//삭제 : <form>전송으로 post 방식으로 처리
	@PostMapping("/delete.do")
	public String boardDeletePost(HttpServletRequest req) {
		//일련번호를 인수로 DAO의 메서드 호출
		int result = dao.delete(req.getParameter("idx"));
		System.out.println("글삭제결과: "+ result);
		return "redirect:list.do";
	}
	
//	@GetMapping("/list.do")
//	public String updatevisitCnt(@RequestParam("idx") int idx, 
//            @RequestParam("visitcount") int visitcount,Model model) {
//		boardService.updateVisitCount(idx, visitcount);
//	}
	
	
}
