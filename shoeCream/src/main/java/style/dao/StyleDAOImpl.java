package style.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import product.bean.ProductDTO;
import style.bean.StyleBoardDTO;
import style.bean.StyleCardDTO;
import style.bean.StyleLikeDTO;
import style.bean.StyleReplyDTO;
import user.bean.UserDTO;

@Repository
public class StyleDAOImpl implements StyleDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<StyleCardDTO> getListOrderByPopularity() {
		return sqlSession.selectList("styleSQL.getListOrderByPopularity");
	}
	
	@Override
	public List<StyleCardDTO> getListOrderByRecentDate() {
		return sqlSession.selectList("styleSQL.getListOrderByRecentDate");
	}
	
	@Override
	public List<StyleCardDTO> getUserFeed(String username) {
		return sqlSession.selectList("styleSQL.getUserFeed", username);
	}
	
	@Override
	public StyleCardDTO getOneStyleCardDTO(int styleId) {
		return sqlSession.selectOne("styleSQL.getOneStyleCardDTO", styleId);
	}
	
	@Override
	public int totalComment(int styleId) {
		return sqlSession.selectOne("styleSQL.totalComment", styleId);
	}
	
	@Override
	public int totalLike(int styleId) {
		return sqlSession.selectOne("styleSQL.totalLike", styleId);
	}
	
	@Override
	public StyleLikeDTO getLikeOnOff(Map<String, Integer> map) {
		return sqlSession.selectOne("styleSQL.getLikeOnOff", map);
	}
	
	@Override
	public void plusLike(Map<String, Integer> map) {
		sqlSession.insert("styleSQL.plusLike1", map);
		sqlSession.update("styleSQL.plusLike2", map.get("styleId"));
	}
	
	@Override
	public void minusLike(Map<String, Integer> map) {
		sqlSession.delete("styleSQL.minusLike1", map);
		sqlSession.update("styleSQL.minusLike2", map.get("styleId"));
	}
	
	@Override
	public int totalStyleCount(int userId) {
		return sqlSession.selectOne("styleSQL.totalStyleCount", userId);
	}
	
	@Override
	public UserDTO getUserByUserId(int userId) {
		return sqlSession.selectOne("styleSQL.getUserByUserId", userId);
	}
	
	@Override
	public UserDTO getUserByUsername(String username) {
		return sqlSession.selectOne("styleSQL.getUserByUsername", username);
	}
	
	@Override
	public Integer getLowestPriceByProductId(int productId) {
		return sqlSession.selectOne("styleSQL.getLowestPriceByProductId", productId);
	}
	
	@Override
	public List<StyleReplyDTO> getReplyList(int styleId) {
		return sqlSession.selectList("styleSQL.getReplyList", styleId);
	}
	

	
	
	// 관리자
	@Override
	public List<StyleBoardDTO> getStyleList(Map<String, Object> map) {
		return sqlSession.selectList("styleSQL.getStyleList", map);
	}
	
	@Override
	public int getTotalStyle(String option) {
		return sqlSession.selectOne("styleSQL.getTotalStyle", option);
	}
	
	@Override
	public List<StyleBoardDTO> searchStyleList(Map<String, Object> map) {
		return sqlSession.selectList("styleSQL.searchStyleList", map);
	}
	
	@Override
	public int getTotalSearchStyle(Map<String, Object> map) {
		return sqlSession.selectOne("styleSQL.getTotalSearchStyle", map);
	}
	
	@Override
	public void deleteStyle(String styleId) {
		sqlSession.delete("styleSQL.deleteStyle",Integer.parseInt(styleId));
	}

}
