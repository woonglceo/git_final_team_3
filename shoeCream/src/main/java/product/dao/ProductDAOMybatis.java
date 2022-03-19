package product.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import product.bean.ProductDTO;
import stock.bean.StockDTO;

@Repository
public class ProductDAOMybatis implements ProductDAO {
	@Autowired
	private SqlSession sqlSession;
	
	// 상품목록
	@Override
	public List<ProductDTO> getproductList(Map<String, Integer> map) {
		return sqlSession.selectList("productSQL.getproductList", map);
	}

	// 상품목록 페이징
	@Override
	public int getTotalProduct() {
		return sqlSession.selectOne("productSQL.getTotalProduct");
	}

	// 상품목록 검색
	@Override
	public List<ProductDTO> searchProduct(Map<String, Object> map) {
		return sqlSession.selectList("productSQL.searchProduct", map);
	}

	// 상품 목록 페이징
	@Override
	public int getSearchTotalProduct(Map<String, Object> map) {
		return sqlSession.selectOne("productSQL.getSearchTotalProduct", map);
	}
	
	// 상품등록
	@Override
	public void getProductWrite(Map<String, Object> map) {
		sqlSession.insert("productSQL.getProductWrite", map);
	}

	// 상품재고량
	@Override
	public List<Object> getProductStock(Map<String, Object> map) {
		List<Integer> sizeList = new ArrayList<>();
		List<Object> list=new ArrayList<>();
		
		System.out.println(map.get("productId"));
		
		for(int i = 0; i < 14; i++) {
			sizeList.add(Integer.parseInt((String) map.get(i*5+225+"")));
		}
		
		map.put("sizeList", sizeList);
		map.put("modelId", sqlSession.selectOne("productSQL.getModelId", Integer.parseInt((String) map.get("productId"))));
		List<StockDTO> stockList = sqlSession.selectList("productSQL.getProductStock", (String) map.get("modelId"));
		
		int count = 0;
		for(StockDTO stockDTO : stockList) {
			for(int i = 0; i < sizeList.size(); i++) {
				if(sizeList.get(i) == stockDTO.getProductSize()) {
					count++;		
					System.out.println(stockDTO.getProductSize() + ", " + count);
					Map<String, String> map3 = new HashMap<String, String>();
					map3.put("productSize", stockDTO.getProductSize()+""); 
					map3.put("count", count+"");
					list.add(map3);
				}				
			}	
			count = 0;
		}

		
		System.out.println(map);

		return list;
	}
	
	
	// 상품삭제
	@Override
	public void deleteProduct(String modelId) {
		sqlSession.delete("productSQL.deleteProduct", modelId);
	}
	
	// 상품수정을 위한 특정 상품의 정보
	@Override
	public List<ProductDTO> getProductInfo(String modelId) {
		return sqlSession.selectList("productSQL.getProductInfo", modelId);
	}
	
	// 상품수정
	@Override
	public void productUpdate(Map<String, Object> map) {
		sqlSession.update("productSQL.productUpdate", map);
	}
	
	@Override
	public ProductDTO getProductById(int productId) {
		return sqlSession.selectOne("productSQL.getProductById", productId);
	}
	
	@Override
	public List<ProductDTO> getProductListForIndex(Map<String, Integer> map) {
		return sqlSession.selectList("productSQL.getProductListForIndex", map);
	}

}
