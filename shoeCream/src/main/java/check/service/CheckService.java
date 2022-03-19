package check.service;

import java.util.List;
import java.util.Map;

import check.bean.CheckDTO;
import check.bean.CheckPaging;

public interface CheckService {

	public List<CheckDTO> getCheckForm(String pg);

	public List<Object> searchBtnForm(Map<String, Object> map);

	public CheckPaging checkPaging(String pg);

}
