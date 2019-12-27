package com.miris.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.miris.dto.EmpDataTableDTO;
import com.miris.vo.EmpVO;

public interface IEmpService {
	public int login(EmpVO vo);
	
	public void logout(HttpSession session);
	
	public EmpVO empDetailInfo(EmpVO vo);
	
	public List<EmpDataTableDTO> empTableList(EmpDataTableDTO tvo);
	
	public List<EmpDataTableDTO> empNameSearch(EmpDataTableDTO tvo);
	
	public List<EmpDataTableDTO> empNameDateSearch(EmpDataTableDTO tvo);
	
	public List<EmpDataTableDTO> empDepartSearch(EmpDataTableDTO tvo);
	
	public List<EmpDataTableDTO> empNameDepartSearch(EmpDataTableDTO tvo);
	
	public int empCount();
	public int empTableCount();
	public int duplicateNameChk(EmpVO vo);
	
}
