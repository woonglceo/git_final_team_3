package mypage.service;

import java.util.List;
import java.util.Map;

import account.bean.AccountDTO;
import address.bean.AddressDTO;
import mypage.bean.MypagePaging;
import user.bean.UserDTO;

public interface MypageService {
	
	public MypagePaging paging(String pg, String tableName, int userId);

	public List<Map<String, Object>> getWishList(String pg, int userId);

	public void deleteWish(int wishListId);

	public List<Map<String, Object>> getBuyList(String pg, int userId);

	public List<Map<String, Object>> getIngBuyingList(String pg, int userId);

	public List<Map<String, Object>> getEndBuyingList(String pg, int userId);
	
	public int getTotalBuying(int userId);

	public MypagePaging ingPaging(String pg, int userId);
	
	public int getTotalIngBuying(int userId);
	
	public MypagePaging endPaging(String pg, int userId);
	
	public int getEndBuying(int userId);
	
	public UserDTO getUser(int userId);

	public void updateUsername(String username);
	
	public void updateEmail(String email);

	public void updatePwd(Map<String, String> map);

	public void updateFullName(String fullName);

	public void updatePhoneNum(String phoneNum);

	public AccountDTO getAccount(int userId);
	
	public void registerAccount(AccountDTO accountDTO);

	public void updateAccount(AccountDTO accountDTO);

	public List<AddressDTO> getAddressList(String pg, int userId);

	public int getIngBuying(int userId);


}
