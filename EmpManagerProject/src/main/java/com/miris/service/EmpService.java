package com.miris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;

import com.miris.dao.EmpDAO;
import com.miris.dto.EmpDataTableDTO;
import com.miris.vo.EmpVO;

@Service
public class EmpService {

	final static int LOGIN = 1;
	final static int PWD_INCORRECT = 2;
	final static int NO_ID = 3;

	@Autowired
	private EmpDAO edao;

	public int login(EmpVO vo) {
		int result = 0;

		if (edao.loginIdCheck(vo) == 1) {
			if (edao.LoginPwdCheck(vo) == 1) {
				result = LOGIN;
			} else {
				result = PWD_INCORRECT;
			}
		} else {
			result = NO_ID;
		}
		return result;
	}

	public void logout(HttpSession session) {
		session.invalidate();
	}
	
	public int duplicateNameChk(EmpVO vo) {
		int result = edao.duplicateNameChk(vo);
		
		return result;
	}

	public EmpVO empDetailInfo(EmpVO vo) {
		return edao.empDetailInfo(vo);
	}

	public List<EmpDataTableDTO> empTableList(EmpDataTableDTO tvo) {
		return edao.EmpTableList(tvo);
	}

	public int empCount() {
		return edao.empCount();
	}
	
	public int empTableCount() {
		return edao.EmpTableCount();
	}

	public List<EmpDataTableDTO> empNameSearch(EmpDataTableDTO tvo) {
		return edao.empNameSearch(tvo);
	}

	public List<EmpDataTableDTO> empNameDateSearch(EmpDataTableDTO tvo) {
		return edao.empNameDateSearch(tvo);
	}

	public List<EmpDataTableDTO> empDepartSearch(EmpDataTableDTO tvo) {
		return edao.empDepartSearch(tvo);
	}

	public List<EmpDataTableDTO> empNameDepartSearch(EmpDataTableDTO tvo) {
		return edao.empNameDepartSearch(tvo);
	}
}
