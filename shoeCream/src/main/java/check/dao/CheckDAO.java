package check.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import check.bean.CheckDTO;

@Repository
@Transactional
public interface CheckDAO {

	public List<CheckDTO> getCheckForm(Map<String, Integer> map);

	public List<CheckDTO> searchBtnForm(Map<String, Integer> map);

}
