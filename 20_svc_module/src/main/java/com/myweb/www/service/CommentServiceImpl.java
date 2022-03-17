package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.repository.CommentDAO;
import com.myweb.www.repository.ProductDAO;

@Service
public class CommentServiceImpl implements CommentService {
	private static Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);
	
	@Inject
	private CommentDAO cdao;
	
	@Inject
	private ProductDAO pdao;

	@Override
	public int register(CommentVO cvo) {
		int isUp = cdao.insert(cvo);
		if (isUp > 0) {
			isUp = pdao.updateCQ(cvo.getPno());
		}
		return isUp;
	}

	@Override
	public List<CommentVO> getList(long pno) {
		return cdao.selectList(pno);
	}

	@Override
	public int modify(CommentVO cvo) {
		return cdao.update(cvo);
	}

	@Override
	public int remove(long cno) {
		long pno = cdao.selectOne(cno);
		int isUp = cdao.delete(cno);
		if (isUp > 0) {
			isUp = pdao.updateDWCQ(pno);
		}
		return isUp;
	}
}
