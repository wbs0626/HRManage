package com.miris.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminVO {
	private String id;			// 관리자ID
	private String pwd;			// 관리자password
	private String permit;	// 관리자 권한 (A : sys, B : normal)
}
