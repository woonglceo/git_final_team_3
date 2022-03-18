package mypage.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mypage.bean.PurchaseDTO;
import mypage.bean.SalesDTO;
import mypage.bean.WishListDTO;

@Repository
public class MypageDAOImpl implements MypageDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<WishListDTO> getWishList(Map<String, Integer> map) {
		return sqlSession.selectList("mypageSQL.getWishList", map);
	}
	
	@Override
	public int getTotalDB(Map<String, Object> map) {
		return sqlSession.selectOne("mypageSQL.getTotalDB", map);
	}
	
	@Override
	public void deleteWish(int wishListId) {
		sqlSession.delete("mypageSQL.deleteWish", wishListId);
	}
	
	@Override
	public void addWish(Map<String, Integer> map) {
		sqlSession.insert("mypageSQL.addWish", map);
	}
	
	@Override
	public WishListDTO getWishOnOff(Map<String, Integer> map) {
		return sqlSession.selectOne("mypageSQL.getWishOnOff", map); //userId, productId
	}
	
	@Override
	public List<PurchaseDTO> getBuyList(Map<String, Integer> map) {
		return sqlSession.selectList("mypageSQL.getBuyList", map);
	}
	
	@Override
	public SalesDTO getPrice(int productId) {
		return sqlSession.selectOne("mypageSQL.getPrice", productId);
	}
		
	@Override
	public int getTotalBuying(int userId) {
		return sqlSession.selectOne("mypageSQL.getTotalBuying", userId);
	}
	
	@Override
	public List<PurchaseDTO> getMonthBuyingList(Map<String, Object> map) {
		return sqlSession.selectList("mypageSQL.getMonthBuyingList", map);
	}
	
	@Override
	public int getTotalMonthBuying(Map<String, Object> map) {
		return sqlSession.selectOne("mypageSQL.getTotalMonthBuying", map);
	}
	
	@Override
	public List<PurchaseDTO> getIngBuyingList(Map<String, Integer> map) {	
		System.out.println(map);
		System.out.println(sqlSession.selectList("mypageSQL.getIngBuyingList", map));
		return sqlSession.selectList("mypageSQL.getIngBuyingList", map);
	}
	
	@Override
	public int getTotalIngBuying(int userId) {
		return sqlSession.selectOne("mypageSQL.getTotalIngBuying", userId);
	}

	@Override
	public List<PurchaseDTO> getEndBuyingList(Map<String, Integer> map) {		
		return sqlSession.selectList("mypageSQL.getEndBuyingList", map);
	}
	
	@Override
	public int getEndBuying(int userId) {
		return sqlSession.selectOne("mypageSQL.getEndBuying", userId);
	}
	
	@Override
	public List<PurchaseDTO> getMonthEndBuyingList(Map<String, Object> map) {
		return sqlSession.selectList("mypageSQL.getMonthEndBuyingList", map);
	}
	
	@Override
	public int getTotalEndMonth(Map<String, Object> map) {
		return sqlSession.selectOne("mypageSQL.getTotalEndMonth", map);
	}
	
	// 판매
	@Override
	public List<SalesDTO> getSellingList(Map<String, Object> map) {
		return sqlSession.selectList("mypageSQL.getSellingList", map);
	}

	@Override
	public int sellpaging(Map<String, Object> map) {
		return sqlSession.selectOne("mypageSQL.sellpaging", map);
	}
	
	@Override
	public List<SalesDTO> getMonthSellingList(Map<String, Object> map) {
		return sqlSession.selectList("mypageSQL.getMonthSellingList", map);
	}
	
	@Override
	public int getMonthSellingPaging(Map<String, Object> map) {
		return sqlSession.selectOne("mypageSQL.getMonthSellingPaging", map);
	}
	
	@Override
	public List<SalesDTO> getEndSellingList(Map<String, Object> map) {
		return sqlSession.selectList("mypageSQL.getEndSellingList", map);
	}
	
	@Override
	public List<SalesDTO> getIngSellingList(Map<String, Object> map) {
		return sqlSession.selectList("mypageSQL.getIngSellingList", map);
	}
	
	@Override
	public int ingSellpaging(Map<String, Object> map) {
		return sqlSession.selectOne("mypageSQL.ingSellpaging", map);
	}

	@Override
	public int endSellpaging(Map<String, Object> map) {
		return sqlSession.selectOne("mypageSQL.endSellpaging", map);
	}
	
	@Override
	public List<SalesDTO> getMonthEndSellingList(Map<String, Object> map) {
		return sqlSession.selectList("mypageSQL.getMonthEndSellingList", map);
	}
	
	@Override
	public int endSellpaging2(Map<String, Object> map) {
		return sqlSession.selectOne("mypageSQL.endSellpaging2", map);
	}
	
	// 구매상세
	@Override
	public Map<String, Object> getByingInfo(Map<String, Object> map) {
		return sqlSession.selectOne("mypageSQL.getByingInfo", map);
	}
	
	// 판매상세
	@Override
	public Map<String, Object> getSellingInfo(Map<String, Object> map) {
		return sqlSession.selectOne("mypageSQL.getSellingInfo", map);
	}
}
