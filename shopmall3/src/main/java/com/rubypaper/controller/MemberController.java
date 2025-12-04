package com.rubypaper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.rubypaper.entity.Member;
import com.rubypaper.service.MemberService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 메인화면
    @GetMapping("/")
    public String main() {
        return "/main";
    }

    // 회원가입 화면
    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("member", new Member());
        return "signup";    
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String signup(
            @Valid Member member,
            BindingResult bindingResult,
            Model model) {

        // 엔티티 유효성 검사 실패
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        // 비밀번호 정규식 검사(해싱 전에)
        String pw = member.getPassword();
        String pwRegex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$";

        if (!pw.matches(pwRegex)) {
            bindingResult.rejectValue("password", "invalid", "비밀번호는 영문, 숫자, 특수문자를 각각 최소 1개 이상 포함해야 합니다.");
            return "signup";
        }

        // 아이디 중복 검사
        if (memberService.isUsernameDuplicate(member.getUsername())) {
            bindingResult.rejectValue("username", "duplicate", "이미 사용 중인 아이디입니다.");
            return "signup";
        }

        // 이메일 중복 검사
        if (memberService.isEmailDuplicate(member.getEmail())) {
            bindingResult.rejectValue("email", "duplicate", "이미 사용 중인 이메일입니다.");
            return "signup";
        }

        // 회원 저장
        memberService.register(member);

        return "redirect:/login";
    }

    // 로그인 화면
    @GetMapping("/login")
    public String loginForm() {
        return "login";   
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(
            String username,
            String password,
            HttpSession session,
            Model model) {

        Member member = memberService.login(username, password);

        if (member == null) {
            model.addAttribute("errorMessage", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "login";
        }

        // 세션 저장
        session.setAttribute("loginMember", member);

        // 메인 페이지로 이동
        return "redirect:/";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
