package com.edu.springboot;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.springboot.jpa.Member;
import com.edu.springboot.jpa.MemberService;

@Controller
public class MainController{


    @GetMapping("/")
    public String home() {
        return "home";
    }
    //자동 주입
    @Autowired
    MemberService memberService;

    // 레코드 입력
    @GetMapping("/insert.do")
    public String insert(Member member, Model model) {
        memberService.insert();
        //View에서 출력할 내용을 Model에 저장
        model.addAttribute("title", "Insert");
        model.addAttribute("result", "레코드 입력 성공");
        model.addAttribute("mode", 0); // 0: 메시지만, 1: 단일행, 2: 복수행
        return "total_view";
    }

    // 전체 조회
    @GetMapping("/selectAll.do")
    public String selectAll(Model model) {
        List<Member> result = memberService.selectAll();
        model.addAttribute("title", "Select All");
        model.addAttribute("result", result);
        model.addAttribute("mode", 2);
        return "total_view";
    }

    // 아이디로 검색
    @GetMapping("/selectById.do")
    public String selectById(@RequestParam("id") Long search, Model model) {
        Optional<Member> result = memberService.selectId(search);
        if (result.isPresent()) {
            model.addAttribute("title", "Select By Id");
            model.addAttribute("result", result.get());
            model.addAttribute("mode", 1);
        } else {
            model.addAttribute("title", "Select By Id");
            model.addAttribute("result", "검색 결과 없음");
            model.addAttribute("mode", 0);
        }
        return "total_view";
    }

    // 이름으로 검색
    @GetMapping("/selectByName.do")
    public String selectByName(@RequestParam("name") String search, Model model) {
        Optional<Member> result = memberService.selectName(search);
        if (result.isPresent()) {
            model.addAttribute("title", "Select By Name");
            model.addAttribute("result", result.get());
            model.addAttribute("mode", 1);
        } else {
            model.addAttribute("title", "Select By Name");
            model.addAttribute("result", "검색 결과 없음");
            model.addAttribute("mode", 0);
        }
        return "total_view";
    }

    // 이메일로 검색
    @GetMapping("/selectByEmail.do")
    public String selectByEmail(@RequestParam("email") String search, Model model) {
        Optional<Member> result = memberService.selectEmail(search);
        if (result.isPresent()) {
            model.addAttribute("title", "Select By Email");
            model.addAttribute("result", result.get());
            model.addAttribute("mode", 1);
        } else {
            model.addAttribute("title", "Select By Email");
            model.addAttribute("result", "검색 결과 없음");
            model.addAttribute("mode", 0);
        }
        return "total_view";
    }

    // 이름으로 LIKE 검색 (내림차순)
    @GetMapping("/selectByNameLikeNameDesc.do")
    public String selectByNameLikeNameDesc(@RequestParam("name") String search, Model model) {
    	//search(검색어)로 시작하는 문자열을 검색
        String name =  search + "%";
        List<Member> result = memberService.selectNameLikeNameDesc(name);
        model.addAttribute("title", "Select By Like Name Desc");
        model.addAttribute("result", result);
        model.addAttribute("mode", 2);
        return "total_view";
    }

    // 이름으로 LIKE 검색 (Sort 사용)
    @GetMapping("/selectByNameLikeOrder.do")
    public String selectByNameLikeOrder(@RequestParam("name") String search, Model model) {
        String name = search + "%";
        Sort sort = Sort.by(Sort.Order.desc("name")); // 내림차순 정렬
        List<Member> result = memberService.selectNameLike(name, sort);
        model.addAttribute("title", "Select By Like Name Desc (Sort 사용)");
        model.addAttribute("result", result);
        model.addAttribute("mode", 2);
        return "total_view";
    }

    // 이름으로 LIKE 검색 (기본)
    @GetMapping("/selectByNameLike.do")
    public String selectByNameLike(@RequestParam("name") String search, Model model) {
        String name = "%" + search + "%";
        List<Member> result = memberService.selectNameLike(name);
        model.addAttribute("title", "Select By Like Name");
        model.addAttribute("result", result);
        model.addAttribute("mode", 2);
        return "total_view";
    }
}
