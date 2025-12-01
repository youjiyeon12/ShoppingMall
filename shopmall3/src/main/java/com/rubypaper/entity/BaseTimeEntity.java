package com.rubypaper.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

//엔티티 생성/수정 시 자동으로 날짜 필드를 채워주는 감시자(Auditing)적용
@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass  //테이블로 생성되지 않고 상속만 되는 클래스
@Getter @Setter
public abstract class BaseTimeEntity {
    public LocalDateTime getRegTime() {
		return regTime;
	}
	public void setRegTime(LocalDateTime regTime) {
		this.regTime = regTime;
	}
	public LocalDateTime getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}
	@CreatedDate
    @Column(updatable = false)  //갱신불가
    private LocalDateTime regTime;
    @LastModifiedDate
    private LocalDateTime updateTime;
}