package deal.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import deal.bean.DealDTO;
import deal.bean.DealPaging;
import deal.dao.DealDAO;


@Service
public class DealServiceImpl implements DealService{
	@Autowired
	private DealDAO dealDAO;	
	@Autowired
	private DealPaging dealPaging;
	
	@Override
	public List<DealDTO> getDealList(String pg) {
		Map<String, Integer> map = new HashMap<String, Integer>(); 
		map.put("endNum", Integer.parseInt(pg) * 5);
		map.put("startNum", (Integer.parseInt(pg) * 5) - 4);
		
		List<DealDTO> list = dealDAO.getDealList(map);
		return list;
	}
	
	@Override
	public DealDTO getDeal(String dealId) {
		return dealDAO.getDeal(dealId);
	}
	
	@Override
	public DealPaging dealPaging(String pg) {
		dealPaging.setCurrentPage(Integer.parseInt(pg)); //현재 페이지
		dealPaging.setPageBlock(6);
		dealPaging.setPageSize(5);
		dealPaging.setTotalA(dealDAO.getTotalCount());
		dealPaging.makePagingHTML();
		
		return dealPaging;
	}
	
	@Override
	public DealPaging dealPaging(Map<String, String> map) {
		return null;
	}
	
	@Override
	public List<DealDTO> getDealSearchList(Map<String, String> map) {
		int endNum = Integer.parseInt(map.get("pg")) * 5;
		int startNum = endNum - 4;
		
		map.put("startNum", Integer.toString(startNum));
		map.put("endNum", Integer.toString(endNum));
		
		return dealDAO.getDealSearchList(map);
	}
	
	
}
