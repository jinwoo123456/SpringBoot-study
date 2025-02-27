import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}

    // 회원가입 페이지 요청
	@RequestMapping("/memberRegist.do")
    public String showRegistPage() {
        return "member/regist"; // View 경로
    }

    // 로그인 페이지 요청
	@RequestMapping("/memberLogin.do")
    public String showLoginPage() {
        return "member/login"; // View 경로
    }

    // 회원가입 처리
	@RequestMapping("/memberRegist.do")
    public String registerMember(MemberDTO memberDTO, Model model) {
        // 회원가입 로직 추가
        model.addAttribute("message", "회원가입 성공!");
        return "redirect:/memberLogin.do"; // 로그인 페이지로 리다이렉트
    }

    // 로그인 처리
	@RequestMapping("/memberLogin.do")
    public String loginMember(LoginDTO loginDTO, Model model) {
        // 로그인 로직 추가
        model.addAttribute("message", "로그인 성공!");
        return "member/dashboard"; // 로그인 후 대시보드로 이동
    }
}
