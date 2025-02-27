package com.edu.springboot.restboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.springboot.jdbc.MemberDTO;
import com.edu.springboot.jsontype4.PersonVO;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class BoardRestController {
	
	@Autowired
	IBoardService dao;
	
	@GetMapping("/restBoardList.do")
	public List<BoardDTO> restBoardList(ParameterDTO parameterDTO){
		//한 페이지에 출력할 게시물의 수 (하드코딩)
		int pageSize =10;
		//페이지 번호
		int pageNum = parameterDTO.getPageNum()==null?1:
			Integer.parseInt(parameterDTO.getPageNum());
		//게시물의 구간 계산 
		int start = (pageNum-1) * pageSize +1;
		int end = pageNum * pageSize;
		//DTO에 계산 결과 저장.
		parameterDTO.setStart(start);
		parameterDTO.setEnd(end);
		//DAO의 메서드 호출
		List<BoardDTO> boardList = dao.lists(parameterDTO);
		//List를 반환하므로 JSON 배열로 화면 출렦된다.
		return boardList;
	}
	
	@GetMapping("restBoardSearch.do")
	public List<BoardDTO> restBoardSearch(HttpServletRequest req,
			ParameterDTO parameterDTO){
		
		//입력된 검색어가 있는 경우..
		if(req.getParameter("searchWord")!=null) {
			//검색어를 스페이스를 통해 split()하여 분리
			String[] sTxtArray = req.getParameter("searchWord")
					.split(" ");
			//저장된 모든 데이터를 삭제
			parameterDTO.getSearchWord().clear();
			//앞에서 반환된 String배열의 크기만큼 반복.
			for(String str : sTxtArray) {
				System.out.println(str);
				//각 검색어를 하나씩 List에 추가한다.
				parameterDTO.getSearchWord().add(str);
			}
			}
		//Mapper의 search 메서드를 호출.
		List<BoardDTO> searchList = dao.search(parameterDTO);
		return searchList;
		}
		
		
	
@GetMapping("/restBoardView.do")
	public BoardDTO restBoardView(ParameterDTO parameterDTO) {
	    BoardDTO boardDTO = dao.view(parameterDTO);
	    return boardDTO;
	}

	
	@PostMapping("restBoardWrite.do")
	public Map<String, Object> writeQuiz(HttpServletRequest req, BoardDTO boardDTO){
		
		
		String id = req.getParameter("id");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		
		
		int result = dao.write(id, title, content);
		System.out.println("글쓰기결과"+result);
		
		
		
		 
		Map<String,Object> map = new HashMap<>();
		 if(result==1) {
			 map.put("result", "성공");
		 }
		 else {
			 map.put("result", "실패");
		 }
		return map;
		
	}
	
//	@PostMapping("/write.do")
//	public String boardWritePost(Model model, HttpServletRequest req) {
//		String name = req.getParameter("name");
//		String title = req.getParameter("title");
//		String content = req.getParameter("content");
//		//파라미터를 개별적으로 전달.
//		int result = dao.write(name, title, content);
//		System.out.println("글쓰기결과"+result);
//		return "redirect:list.do";
//	}
}
