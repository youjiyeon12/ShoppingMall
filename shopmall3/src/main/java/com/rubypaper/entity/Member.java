package com.rubypaper.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  

    @NotBlank(message = "아이디는 필수 입력값입니다.")
    @Pattern(
	    regexp = "^[a-zA-Z0-9]{5,20}$",
	    message = "아이디는 영문과 숫자만 사용하여 5~20자로 입력해주세요."
	)
    @Column(nullable = false, unique = true, length = 30)
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Column(nullable = false)
    private String password;
    
    @Transient 
    @NotBlank(message = "비밀번호 확인은 필수입니다.")
    private String passwordConfirm;

    @NotBlank(message = "이름을 입력해주세요.")
    @Size(max = 20, message = "이름은 최대 20자까지 가능합니다.")
    @Column(nullable = false, length = 20)
    private String name;

    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Email(message = "올바른 이메일 주소를 입력하세요.")
    @Pattern(
	    regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
	    message = "이메일 형식이 올바르지 않습니다. (예: example@gmail.com)"
	)
    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false)
    private String role = "USER";
}

