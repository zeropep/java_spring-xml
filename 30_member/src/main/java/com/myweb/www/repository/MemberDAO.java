package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.MemberVO;

public interface MemberDAO {
	int insert(MemberVO mvo);
	MemberVO selectOne(MemberVO mvo);
	List<MemberVO> selectList();
	MemberVO selectOne(String email);
	int update(MemberVO mvo);
	int delete(String email);
	int update(String email); //  
}
