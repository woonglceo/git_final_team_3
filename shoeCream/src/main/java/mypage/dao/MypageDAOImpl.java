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
		/*
		 * Date today = new Date(); sqlSession.update("mypageSQL.updateStaus", today);
		 */
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
	public List<PurchaseDTO> getMonthBuyingList2(Map<String, Object> map) {
		System.out.println(map);
		return sqlSession.selectList("mypageSQL.getMonthBuyingList2", map);
	}
	
	@Override
	public int getTotalMonthBuying(int userId) {
		return sqlSession.selectOne("mypageSQL.getTotalMonthBuying", userId);
	}
	
	@Override
	public int getTotalMonthBuying2(Map<String, Object> map) {
		return sqlSession.selectOne("mypageSQL.getTotalMonthBuying2", map);
	}
	
	@Override
	public List<PurchaseDTO> getIngBuyingList(Map<String, Integer> map) {		
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
	
	@Override
	public List<PurchaseDTO> getMonthEndBuyingList3(Map<String, Object> map) {
		return sqlSession.selectList("mypageSQL.getMonthEndBuyingList3", map);
	}
	
	@Override
	public int getTotalMonthBuying3(Map<String, Object> map) {
		return sqlSession.selectOne("mypageSQL.getTotalMonthBuying3", map);
	}
}
