package com.edu.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
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
	
	// Mybatis 사용을 위한 인터페이스 자동주입
	@Autowired
	ITicketService dao;
	
	/*
	 * 트랜잭션 처리를 위한 빈 자동주입.
	 * TransactionTemplate을 사용하여 트랜잭션을 관리
	 * */
	
	//트렌잭션 처리를 위한 템플릿 빈을 자동주입
	@Autowired
	TransactionTemplate transactionTemplate;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	// 티켓 구매 페이지에 대한 매핑 처리
	@GetMapping("/buyTicket.do")
	public String buy1() {
		return "buy";
	}
	
	// 구매 처리
	@PostMapping("/buyTicket.do")
	public String buy2(TicketDTO ticketDTO, PayDTO payDTO, HttpServletRequest req, Model model) {
		// 구매에 성공한 경우 포워드할 View 경로 설정 (초기값: 성공)
		String viewPath = "success";
		
		/* 템플릿을 사용하면 기존의 Status 인스턴스는 필요없으므로 삭제한다. */
		try {
			/*템플릿 내에 익명클래스를 오버라이딩된 메서드로 모든 비즈니스
			 * 로직을 옮겨주면된다. 템플릿을 사용하면 commit,rollback을 별도로 기술하지 않아도 자동으로 트랜잭션 처리된다.*/
			// TransactionTemplate을 사용해 트랜잭션 실행
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(org.springframework.transaction.TransactionStatus status) {
					
					// 1. DB 처리: 구매 금액 입력 (구매장수 * 10000원)
					payDTO.setAmount(ticketDTO.getT_count() * 10000);
					int result1 = dao.payInsert(payDTO);
					if(result1 == 1) {
						System.out.println("transaction_pay 입력 성공");
					}
					
					// 2. 비즈니스 로직 처리: 의도적인 에러 발생 (에러 체크박스가 선택된 경우)
					String errFlag = req.getParameter("err_flag");
					if(errFlag != null) {
						// 이 코드가 실행되면 NumberFormatException이 발생하여 rollback 됨
						int money = Integer.parseInt("100원");
					}
					
					// 3. DB 처리: 구매한 티켓 매수 입력 (예: 6장 이상 구매시 제약조건 위배 등)
					int result2 = dao.ticketInsert(ticketDTO);
					if(result2 == 1) {
						System.out.println("transaction_ticket 입력 성공");
					}
					
					// DTO를 Model에 저장
					model.addAttribute("ticketDTO", ticketDTO);
					model.addAttribute("payDTO", payDTO);
					
					// 정상적으로 처리되면 TransactionTemplate이 자동으로 commit
				}
			});
		} catch(Exception e) {
			e.printStackTrace();
			// 에러 발생 시 viewPath를 error 페이지로 설정 (트랜잭션은 자동으로 rollback)
			viewPath = "error";
		}
		
		return viewPath;
	}
}
