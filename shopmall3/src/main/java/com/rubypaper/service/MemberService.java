package com.rubypaper.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rubypaper.entity.Member;
import com.rubypaper.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	
	// 아이디 중복 체크
	public boolean isUsernameDuplicate(String username) {
		return memberRepository.findByUsername(username) != null;
	}
	
	// 이메일 중복 체크
	public boolean isEmailDuplicate(String email) {
		return memberRepository.findByEmail(email) != null;
	}
	
	// 회원 저장
	public Member register(Member member) {
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    member.setPassword(encoder.encode(member.getPassword()));
		return memberRepository.save(member);
	}
	
	// 로그인
	public Member login(String username, String password) {
	    Member member = memberRepository.findByUsername(username);

	    if (member == null) return null;

	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    if (!encoder.matches(password, member.getPassword())) return null;

	    return member;
	}
}
