package mypage.dao;

import java.util.List;
import java.util.Map;

import mypage.bean.PurchaseDTO;
import mypage.bean.SalesDTO;
import mypage.bean.WishListDTO;

public interface MypageDAO {

	public List<WishListDTO> getWishList(Map<String, Integer> map);

	public int getTotalDB(Map<String, Object> map);

	public void deleteWish(int wishListId);
	
	public List<PurchaseDTO> getBuyList(Map<String, Integer> map);

	public SalesDTO getPrice(int productId);

	public int getTotalBuying(int userId);

	public List<PurchaseDTO> getIngBuyingList(Map<String, Integer> map);

	public int getTotalIngBuying(int userId);

	public  List<PurchaseDTO> getEndBuyingList(Map<String, Integer> map);

	public int getEndBuying(int userId);

	public int getIngBuying(int userId);

}
