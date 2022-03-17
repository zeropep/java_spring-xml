package com.myweb.www.repos;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.MemberVO;
import com.myweb.www.repository.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberTest {
	private static Logger logger = LoggerFactory.getLogger(MemberTest.class);
	
	@Inject
	private MemberDAO mdao;
	
	@Test
	public void insertMemberDummy() throws Exception {
		MemberVO mvo = new MemberVO();
		mvo.setEmail("tester@tester.com");
		mvo.setNickName("tester");
		mvo.setPwd("asd");
		mdao.insert(mvo);
	}
	
	@Test
	public void insertMemberDummies() throws Exception {
		for (int i = 0; i < 100; i++) {
			MemberVO mvo = new MemberVO();
			mvo.setEmail("tester" + i + "@tester.com");
			mvo.setNickName("tester" + i);
			mvo.setPwd("asd");
			mdao.insert(mvo);
		}
	}
	
	@Test
	public void getListMembers() throws Exception {
		List<MemberVO> list = mdao.selectList();
		for (MemberVO mvo : list) {
			logger.info(">>> {}, {}, {}, {}, {}",
					mvo.getEmail(), mvo.getNickName(), mvo.getRegAt(), mvo.getLastLogin(), mvo.getGrade());
		}
	}
	
	@Transactional
	@Test
	public void loginMember() throws Exception {
		MemberVO mvo = new MemberVO();
		mvo.setEmail("tester@tester.com");
		mvo.setPwd("asd");
		mdao.update(mvo.getEmail());
		MemberVO loginMvo = mdao.selectOne(mvo);
		logger.info(">>> {}, {}, {}, {}",
					loginMvo.getEmail(), loginMvo.getNickName(), loginMvo.getLastLogin(), loginMvo.getGrade());
	}
	
	@Test
	public void getDetailMember() throws Exception {
		MemberVO mvo = mdao.selectOne("tester@tester.com");
		logger.info(">>> {}", mvo.toString());
	}
	
	@Test
	public void modifyMember() throws Exception {
		MemberVO mvo = new MemberVO();
		mvo.setEmail("tester@tester.com");
		mvo.setPwd("asd");
		mvo.setNickName("tester");
		int isUp = mdao.update(mvo);
		logger.info(">>> Modify {}", isUp > 0 ? "Success" : "Fail");
	}
	
	@Test
	public void deleteMember() throws Exception {
		int isUp = mdao.delete("tester@tester.com");
		logger.info(">>> Remove {}", isUp > 0 ? "Success" : "Fail");
	}
}
