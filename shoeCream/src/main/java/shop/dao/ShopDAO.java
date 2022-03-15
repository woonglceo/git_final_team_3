package shop.dao;

import java.util.List;
import java.util.Map;

import shop.bean.ShopDTO;

public interface ShopDAO {

	public List<ShopDTO> getShopList(Map<String, Integer> map);

	public ShopDTO getShop(String productId);

	public int getTotalCount();

	public List<ShopDTO> getShopSearchList(Map<String, String> map);


}
