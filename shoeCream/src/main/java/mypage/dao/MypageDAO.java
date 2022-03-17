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

	public void addWish(Map<String, Integer> map);
	
	public List<PurchaseDTO> getBuyList(Map<String, Integer> map);

	public SalesDTO getPrice(int productId);

	public int getTotalBuying(int userId);

	public List<PurchaseDTO> getIngBuyingList(Map<String, Integer> map);

	public int getTotalIngBuying(int userId);
	
	public List<PurchaseDTO> getMonthBuyingList(Map<String, Object> map);
	
	public List<PurchaseDTO> getMonthBuyingList2(Map<String, Object> map);

	public  List<PurchaseDTO> getEndBuyingList(Map<String, Integer> map);

	public int getEndBuying(int userId);

	public int getTotalMonthBuying(int userId);
	
	public int getTotalMonthBuying2(Map<String, Object> map);

	public List<PurchaseDTO> getMonthEndBuyingList(Map<String, Object> map);

	public int getTotalEndMonth(Map<String, Object> map);

	public List<PurchaseDTO> getMonthEndBuyingList3(Map<String, Object> map);

	public int getTotalMonthBuying3(Map<String, Object> map);

	public WishListDTO getWishOnOff(Map<String, Integer> map);


}
