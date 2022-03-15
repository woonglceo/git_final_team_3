package check.service;

import java.util.List;

import check.bean.CheckDTO;

public interface CheckService {

	public List<CheckDTO> getCheckForm(String pg);

	public List<CheckDTO> searchBtnForm(String pg);

}
