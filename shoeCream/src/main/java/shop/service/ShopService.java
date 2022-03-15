package shop.service;

import java.util.List;
import java.util.Map;

import shop.bean.ShopDTO;
import shop.bean.ShopPaging;

public interface ShopService {
	public List<ShopDTO> getShopList(String pg);

	public ShopDTO getShop(String productId);

	public ShopPaging shopPaging(String pg);
	public ShopPaging shopPaging(Map<String, String> map);

	public List<ShopDTO> getShopSearchList(Map<String, String> map);

}
