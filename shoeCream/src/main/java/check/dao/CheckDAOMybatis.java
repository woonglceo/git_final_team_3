package check.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import check.bean.CheckDTO;


@Repository
@Transactional
public class CheckDAOMybatis implements CheckDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<CheckDTO> getCheckForm(Map<String, Integer> map) {
		return sqlSession.selectList("checkSQL.getCheckForm", map);
	}

	@Override
	public List<CheckDTO> searchBtnForm(Map<String, Integer> map) {
		return sqlSession.selectList("checkSQL.searchBtnForm", map);

	}

}
