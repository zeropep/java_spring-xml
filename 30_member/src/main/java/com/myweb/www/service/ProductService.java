package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.ProductVO;

public interface ProductService {
	int register(ProductVO pvo);
	List<ProductVO> getList();
	ProductVO selectOne(long pno);
	ProductVO selectForMod(long pno);
	int modify(ProductVO pvo);
	int remove(long pno);
}
