package com.edu.springboot;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@RequestMapping("/")
	public String home() {
		
		return "home";
	}
	
	/*
	 * 컨트롤러에서 매핑을 위한 @ResponseBody 어노테이션을 부착하면
	 * 반환되는 값을 웹브라우저에 출력할 수 있다.
	 * */
	@RequestMapping("/json.do")
	@ResponseBody
	public String json() {
		/*
		 * 외부라이브러리인 simple-json을 통해 사용할 수 있는 클래스로
		 * JSON배열과 객체를 생성해준다.사용법은 List,Map 과 동일하다.*/
		JSONArray arr = new JSONArray();
		JSONObject obj = new JSONObject();
		
		
		//JSON객체에 데이터 추가
		arr.add("손오공");
		arr.add("저팔계");
		arr.add("사오정");
		obj.put("서유기",arr);
		obj.put("result",1);
		
		//json을 String 형식으로 웹브라우저에 출력한다.
		return obj.toJSONString();
		
	}
	@RequestMapping("/json2.do")
	@ResponseBody
	public String showJSON() {
		JSONArray hobby = new JSONArray();
		JSONArray mid = new JSONArray();
		JSONArray circle = new JSONArray();
		JSONArray class2 = new JSONArray();
		JSONObject obj = new JSONObject();
		JSONObject friendObj = new JSONObject();
		JSONObject highObj = new JSONObject();

		
		
		
		hobby.add("자전거");
		hobby.add("수영");
		hobby.add("축구");
		
		mid.add("손오공");
		mid.add("저팔계");
		mid.add("사오정");
		
		circle.add("유비");
		circle.add("관우");
		circle.add("장비");
		
		class2.add("이몽룡");
		class2.add("성춘향");
		
		obj.put("name","홍길동");
		obj.put("age","99");
		obj.put("hobby", hobby);
		
		friendObj.put("mid", mid);
		friendObj.put("high", highObj);
		highObj.put("circle", circle);
		highObj.put("class2", class2);
		
		obj.put("friend",friendObj);
		
		System.out.println(obj+" << obj");
		
		


		
		
		
		return obj.toJSONString();
		
	}
	/*
	 * {
	"name":"홍길동",
	"age":99,
	"hobby":["자전거","수영","축구"],
	"friend":{
		"mid":["손오공","저팔계","사오정"],
		"high":{
			"circle":["유비","관우","장비"],
			"class":["이몽룡","성춘향"]
		}
	}
}

	 * */
	
}
