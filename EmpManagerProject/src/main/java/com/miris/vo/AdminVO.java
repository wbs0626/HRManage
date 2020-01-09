package com.miris.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminVO {
	private String id;			// 관리자ID
	private String pwd;			// 관리자password
	private String admin_right;	// 관리자 권한 (A : sys, B : normal)
}
