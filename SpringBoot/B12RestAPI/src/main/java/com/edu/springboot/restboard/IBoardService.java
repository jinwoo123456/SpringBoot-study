package com.edu.springboot.restboard;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IBoardService {

	//게시물 갯수
	public int totalCount();
	
	//게시물 가져오기
	public ArrayList<BoardDTO> lists(ParameterDTO parameterDTO);
	//게시물 검색하기
	public ArrayList<BoardDTO> search(ParameterDTO parameterDTO);
	public BoardDTO view(ParameterDTO parameterDTO);
	
	
	//게시물 작성
	public int write(@Param("_id") String name,
			@Param("_title") String title,
			@Param("_content") String content);
}
