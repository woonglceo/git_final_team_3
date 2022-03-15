package address.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import address.bean.AddressDTO;

@Repository
@Transactional
public class AddressDAOMybatis implements AddressDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<AddressDTO> getAddressList(Map<String, Integer> map) {
		return sqlSession.selectList("addressSQL.getAddressList", map);
	}

}
