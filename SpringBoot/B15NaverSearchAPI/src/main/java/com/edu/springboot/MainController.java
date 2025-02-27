package com.edu.springboot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class MainController {

	@RequestMapping("/")
	public String home() {
		
		return "home";
	}
	
	@RequestMapping("/NaverSearchMain.do")
	public String NaverSearchMain() {
		return "SearchView";
	}
	
	
	/*
	 * responseBody가 있다는건 컨트롤러에서 바로 출력하겠다는것.
	 * 메서드에서 반환되는 값은 웹브라우저에 즉시 출력된다. VIew로 포워드하지 않는다.
	 * */
	@ResponseBody
	@GetMapping("/NaverSearchRequest.do")
	 public String NaverSearchRequest(HttpServletRequest req,
			 HttpServletResponse resp) {
			
		
		    //1. 인증 정보 설정.
			//네이버 검색 API 키
	        String clientId = "dsYS8Xqjs_GFAdW2Vi6P"; //애플리케이션 클라이언트 아이디
	        String clientSecret = "gvLO9I_VYf"; //애플리케이션 클라이언트 시크릿

	        
	        // 2. 검색 조건 설정
	        int startNum = 0; //검색 시작 위치(페이지 번호)
	        String text = null; //사용자가 입력한 검색어
	        try {
	        	//파라미터를 받은 후 정수로 변환.
	        	startNum = Integer.parseInt(req.getParameter("startNum"));
	        	//검색어의 경우 UTF-8로 인코딩 처리
	        	String searchText = req.getParameter("keyword");
	            text = URLEncoder.encode(searchText, "UTF-8");
	        } catch (UnsupportedEncodingException e) {
	            throw new RuntimeException("검색어 인코딩 실패",e);
	        }

	        
	       // 3 . api url 조합
	        /* 사용자가 입력한 값을 통해 요청url을 조립한 후 설정을 완료한다.*/
	        String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text
	        		+"&display=10&start="+startNum;    // JSON 결과
	        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // XML 결과

	        //4. API 호출
	        Map<String, String> requestHeaders = new HashMap<>();
	        requestHeaders.put("X-Naver-Client-Id", clientId);
	        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
	        String responseBody = get(apiURL,requestHeaders);

	        //5 결과출력
	        System.out.println(responseBody);
	        return responseBody;
	    }


	    private static String get(String apiUrl, Map<String, String> requestHeaders){
	        HttpURLConnection con = connect(apiUrl);
	        try {
	            con.setRequestMethod("GET");
	            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
	                con.setRequestProperty(header.getKey(), header.getValue());
	            }


	            int responseCode = con.getResponseCode();
	            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
	                return readBody(con.getInputStream());
	            } else { // 오류 발생
	                return readBody(con.getErrorStream());
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("API 요청과 응답 실패", e);
	        } finally {
	            con.disconnect();
	        }
	    }


	    private static HttpURLConnection connect(String apiUrl){
	        try {
	            URL url = new URL(apiUrl);
	            return (HttpURLConnection)url.openConnection();
	        } catch (MalformedURLException e) {
	            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
	        } catch (IOException e) {
	            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
	        }
	    }


	    private static String readBody(InputStream body){
	        InputStreamReader streamReader = new InputStreamReader(body);


	        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
	            StringBuilder responseBody = new StringBuilder();


	            String line;
	            while ((line = lineReader.readLine()) != null) {
	                responseBody.append(line);
	            }


	            return responseBody.toString();
	        } catch (IOException e) {
	            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
	        }
	    }
	    
//	@ResponseBody
//	@RequestMapping("NaverSearchRequest.do")
//	public String NaverSearchRequest(HttpServletRequest req,
//			HttpServletResponse resp) {
//		String clientId = "dsYS8Xqjs_GFAdW2Vi6P";//클라이언트 아이디
//		String clientSecret = "gvLO9I_VYf"; //클라이언트 시크릿
//		
//		//2. 검색조건 설정
//		int startNum = 0; //검색 시작 위치
//		String text = null; //검색어
//		try {
//			startNum = Integer.parseInt(req.getParameter("startNum"));
//			String searchText = req.getParameter("keyword");
//			text = URLEncoder.encode(searchText,"UTF-8");
//			
//		}catch ( UnsupportedEncodingException e) {
//			throw new RuntimeException("검색어 인코딩 실패",e);
//		}
//		
//		//3.API URL 조합
//		String apiURL = "https://openapi.naver.com/v1/search/blog?"
//				+"query=" + text + "&display=10&start="+startNum; //json콜백
//		
//		//String apiURL = "https://openapi.naver.com/v1/search/blog.xml?"
//		// query="+text; // xml콜백
//		//4.API호출
//		Map<String,String> requestHeaders = new HashMap<>();
//		requestHeaders.put("X-Naver-Client", clientId);
//		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
//		String responseBody = get(apiURL,requestHeaders);
//		//5.결과 출력
//		System.out.println(responseBody);
//		return responseBody;
//	}
//	private static String get(String apiUrl, Map<String, String> requestHeaders) {
//		private static HttpURLConnection connect(String apiUrl) {
//			private staticString readBody(InputStream body) {
//				
//			}
//		}
//	}
}
