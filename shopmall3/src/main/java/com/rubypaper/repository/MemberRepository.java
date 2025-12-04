package com.rubypaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rubypaper.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Member findByUsername(String username);
	Member findByEmail(String email);
}
