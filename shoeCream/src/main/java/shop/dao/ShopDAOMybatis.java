package shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import shop.bean.ShopDTO;

@Repository
@Transactional
public class ShopDAOMybatis implements ShopDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<ShopDTO> getShopList(Map<String, Integer> map) {
		return sqlSession.selectList("shopSQL.getShopList", map);
	}
	
	@Override
	public ShopDTO getShop(String productId) {
		return sqlSession.selectOne("shopSQL.getShop", Integer.parseInt(productId));
	}
	
	@Override
	public int getTotalCount() {
		return sqlSession.selectOne("shopSQL.getTotalCount");
	}
	
	@Override
	public List<ShopDTO> getShopSearchList(Map<String, String> map) {
		return sqlSession.selectList("shopSQL.getShopSearchList", map);
	}
}
