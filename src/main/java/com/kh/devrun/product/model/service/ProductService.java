package com.kh.devrun.product.model.service;

import java.util.List;
import java.util.Map;

import com.kh.devrun.category.model.vo.ProductChildCategory;
import com.kh.devrun.product.model.vo.Product;
import com.kh.devrun.product.model.vo.ProductCategory;
import com.kh.devrun.product.model.vo.ProductDetail;
import com.kh.devrun.product.model.vo.ProductExtends;

public interface ProductService {
	List<ProductChildCategory> selectChildCategory(Map<String, Object> param);

	int insertProduct(Map<String,Object> param);

	int insertProducCategory(ProductCategory productCategory);

	int insertProductDetail(ProductDetail productDetail);

	// 상품 리스트 조회
	List<Product> selectAllProductList(int offset, int limit);
	
	int selectTotalBoardCount();

	// 비동기로 옵션값 가져오기
	List<ProductDetail> findProductOption(String productCode);

	// 상품삭제
	int deleteProduct(String productCode);
	
	// 상품 정보 하나 가져오기
	ProductExtends selectProductOne(String productCode);
	
}
