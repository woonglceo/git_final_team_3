package check.service;

import java.util.List;
import java.util.Map;

import check.bean.CheckDTO;

public interface CheckService {

	public List<CheckDTO> getCheckForm(String pg);

	public void searchBtnForm(Map<String, Object> map);

}
