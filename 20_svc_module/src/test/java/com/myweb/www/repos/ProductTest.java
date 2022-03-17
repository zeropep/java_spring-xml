package com.myweb.www.repos;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myweb.www.domain.ProductVO;
import com.myweb.www.repository.CommentDAO;
import com.myweb.www.repository.ProductDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ProductTest {
	private static Logger logger = LoggerFactory.getLogger(ProductTest.class);
	
	@Inject
	private ProductDAO pdao;
	private CommentDAO cdao;
	
	@Test
	public void insertProductDummies() throws Exception {
		for (int i = 0; i < 50; i++) {
			ProductVO pvo = new ProductVO();
			pvo.setCategory("test");
			pvo.setPname("testItem" + i);
			pvo.setPrice(100);
			pvo.setWriter("tester1@tester.com");
			pvo.setDescription("test item" + i + "is uploaded");
			pvo.setMadeBy("Factory");
			int isUp = pdao.insert(pvo);
			logger.info(">>> Insert {}", isUp > 0 ? "Success" : "Fail");			
		}
	}
	
	@Test
	public void getListProduct() throws Exception {
		List<ProductVO> list = pdao.selectList();
		for (ProductVO pvo : list) {
			logger.info(">>> {}, {}, {}, {}, {}, {}",
						pvo.getPno(), pvo.getCategory(), pvo.getPname(), pvo.getPrice(),
						pvo.getReadCount(), pvo.getCmtQty());
		}
	}
	
	@Test
	public void getDetailProduct() throws Exception {
		
		ProductVO pvo = pdao.selectOne(1L);
		logger.info(">>> pvo {}", pvo.toString());
	}
	
	@Test
	public void modifyProduct() throws Exception {
		ProductVO pvo = new ProductVO();
		pvo.setPno(1L);
		pvo.setCategory("testCate");
		pvo.setPname("testItem");
		pvo.setPrice(200);
		pvo.setDescription("test Modify");
		pvo.setMadeBy("test madeby");
		int isUp = pdao.update(pvo);
		logger.info(">>> Modify {}", isUp > 0 ? "Success" : "Fail");	
	}
	
	@Test
	public void removeProduct() throws Exception {
		cdao.deleteAll(1L);
		int isUp = pdao.delete(1L);
		logger.info(">>> Remove {}", isUp > 0 ? "Success" : "Fail");	
	}
}
