package com.miris.vo;

/*
 * 	부서 정보
 */

import java.util.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DepartVO {
	private int depart_id;
	private String depart_name;
	private String reg_date;
	private String departs_remarks;
	
}
