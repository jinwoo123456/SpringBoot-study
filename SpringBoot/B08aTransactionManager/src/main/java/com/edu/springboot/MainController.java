package com.edu.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.springboot.jdbc.ITicketService;
import com.edu.springboot.jdbc.PayDTO;
import com.edu.springboot.jdbc.TicketDTO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {
	
	//Mybatis 사용을 위한 인터페이스 자동주입
	@Autowired
	ITicketService dao;
	/*
	 * 트랜잭션 처리를 위한 bin 자동주입. 별도의 설정없이 스프링 컨테이너가
	 * 미리 생성해둔것을 자동으로 주입받아 사용할 수 있다.
	 * */
	@Autowired
	PlatformTransactionManager transactionManager;
	
	@Autowired
	TransactionDefinition definition;
	
	

	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	//티켓구매 페이지에 대한 매핑 처리
	@GetMapping("/buyTicket.do")
	public String buy1() {
		return "buy";
	}
	//구매 처리
	@PostMapping("/buyTicket.do")
	public String buy2(TicketDTO ticketDTO, PayDTO payDTO,
			HttpServletRequest req,Model model) {
		//구매에 성공한 경우 포워드 할 View 경로 설정
		String viewPath = "success";
		/*
		 * 자동주입된 빈을 통해 Status 인스턴스를 생성한다. 이를 통해 트랜잭션
		 * 처리를 하게 된다.
		 * */
		TransactionStatus status = transactionManager.getTransaction(definition);
		try {
			//DB처리1 : 구매금액에 대한 입력처리. 구매장수 * 10000원
			payDTO.setAmount(ticketDTO.getT_count()*10000);
			//insert 성공시 콘솔에 로그 출력
			int result1 = dao.payInsert(payDTO);
			if(result1==1) System.out.println("transaction_pay 입력 성공");
			
			//2.비즈니스 로직 처리 (의도적인 에러 발생)
			String errFlag = req.getParameter("err_flag");
			if(errFlag!=null) {
				/*구매페이지에서 체크박스에 체크한 경우 이 코드가 실핼되어
				 * NumberFormatException이 발생한다.
				 * 문자("원")을 정수로 변환할 수 없기 때문.*/
				int money = Integer.parseInt("100원");
				
			}
			/*3. DB처리2 : 구매한 티켓 매수에 대한 처리로 6장 이상이면
			 * check 제약조건 위배로 DB에러가 발생된다.*/
			int result2 = dao.ticketInsert(ticketDTO);
			if(result2==1) System.out.println("transcation_ticekt 입력 성공");
			
			//DTO를 Model에 저장
			model.addAttribute("ticketDTO", ticketDTO);
			model.addAttribute("payDTO",payDTO);
			
			//모든 작업에 대해 정상적으로 처리되었다면 commit 한다.
			transactionManager.commit(status);
			
		}
		catch(Exception e) {
			e.printStackTrace();
			/*
			 * 위 3개의 단위작업 중 하나라도 오류가 발생되면 전체 작업을
			 * rollback 한 후 에러페이지로 포워드한다.
			 * */
			viewPath ="error";
			transactionManager.rollback(status);
			
		}
		
		return viewPath;
	}
	
		
}
