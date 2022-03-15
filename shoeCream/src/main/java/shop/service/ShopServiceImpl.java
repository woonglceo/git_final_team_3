package shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import product.dao.ProductDAO;
import shop.bean.ShopDTO;
import shop.bean.ShopPaging;
import shop.dao.ShopDAO;

@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopDAO shopDAO;
	@Autowired
	private ShopPaging shopPaging;
	private int NUMBER_OF_ITEMS_PER_PG = 24;
	
	@Override
	public List<ShopDTO> getShopList(String pg) {
		Map<String, Integer> map = new HashMap<String, Integer>(); 
		map.put("endNum", Integer.parseInt(pg) * NUMBER_OF_ITEMS_PER_PG);
		map.put("startNum", (Integer.parseInt(pg) * NUMBER_OF_ITEMS_PER_PG) - (NUMBER_OF_ITEMS_PER_PG - 1));
		
		List<ShopDTO> list = shopDAO.getShopList(map);
		return list;
	}
	
	@Override
	public ShopDTO getShop(String productId) {
		return shopDAO.getShop(productId);
	}
	
	@Override
	public ShopPaging shopPaging(String pg) {
		shopPaging.setCurrentPage(Integer.parseInt(pg)); //�쁽�옱 �럹�씠吏�
		shopPaging.setPageBlock(6);
		shopPaging.setPageSize(5);
		shopPaging.setTotalA(shopDAO.getTotalCount());
		shopPaging.makePagingHTML();
		
		return shopPaging;
	}
	
	@Override
	public ShopPaging shopPaging(Map<String, String> map) {
		return null;
	}
	
	@Override
	public List<ShopDTO> getShopSearchList(Map<String, String> map) {
		int endNum = Integer.parseInt(map.get("pg")) * 5;
		int startNum = endNum - 4;
		
		map.put("startNum", Integer.toString(startNum));
		map.put("endNum", Integer.toString(endNum));
		
		return shopDAO.getShopSearchList(map);
	}
	
	
}
