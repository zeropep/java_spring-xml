package com.myweb.www.svc;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.service.CommentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class CommentTest {
	private static Logger logger = LoggerFactory.getLogger(CommentTest.class);
	
	@Inject
	private CommentService csv;
	
	@Test
	public void insertCommentDummies() throws Exception {
		for (int i = 50; i > 20; i--) {
			int rcount = (int) (Math.random() * 32);
			for (int j = 0; j < rcount; j++) {
				CommentVO cvo = new CommentVO();
				cvo.setContent("This is Comment of " + i + " > " + j);
				cvo.setWriter("tester" + (int) (Math.random() * 100) + "@tester.com");
				cvo.setPno(i);
				csv.register(cvo);
			}
		}
	}
	
	@Test
	public void getListComment() throws Exception {
		csv.getList(50L);
	}
	
	@Test
	public void modifyComment() throws Exception {
		CommentVO cvo = new CommentVO();
		cvo.setCno(1L);
		cvo.setContent("content modified");
		csv.modify(cvo);
	}
	
	@Test
	public void removeComment() throws Exception {
		csv.remove(1L);
	}
}
