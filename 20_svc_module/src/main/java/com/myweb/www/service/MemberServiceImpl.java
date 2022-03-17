package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.myweb.www.domain.MemberVO;
import com.myweb.www.repository.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	private static Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Inject
	private MemberDAO mdao;

	@Override
	public int register(MemberVO mvo) {
		return mdao.insert(mvo);
	}

	@Override
	public MemberVO login(MemberVO mvo) {
		MemberVO loginMvo = mdao.selectOne(mvo);
		if (loginMvo != null) {
			mdao.update(mvo.getEmail());	
			// 이 수정사항은 미리 불러온 loginMvo에 반영되어있지 않다.
			// 어차피 로그인했을때는 그 이전접속기록이 필요하기 때문에 반영해줄 필요가 없지만,
			// 반영되길 원한다면 다시 불러오면 된다.
		}
		return loginMvo;
	}

	@Override
	public List<MemberVO> getList() {
		return mdao.selectList();
	}

	@Override
	public MemberVO getDetail(String email) {
		return mdao.selectOne(email);
	}

	@Override
	public int modify(MemberVO mvo) {
		return mdao.update(mvo);
	}

	@Override
	public int remove(String email) {
		return mdao.delete(email);
	}
}
