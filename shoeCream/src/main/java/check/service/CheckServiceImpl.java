package check.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import check.bean.CheckDTO;
import check.dao.CheckDAO;


@Service
public class CheckServiceImpl implements CheckService{
	@Autowired
	private CheckDAO checkDAO;
	
	@Override
	public List<CheckDTO> getCheckForm(String pg) {
		Map<String, Integer> map = new HashMap<String, Integer>(); 
		map.put("pg", Integer.parseInt(pg));
		
		List<CheckDTO> list = checkDAO.getCheckForm(map);
		System.out.println("checkService list: "+list);
		return list;
	}

	@Override
	public void searchBtnForm(Map<String, Object> map) {
		 checkDAO.searchBtnForm(map);

	}

}
