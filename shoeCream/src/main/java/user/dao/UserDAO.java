package user.dao;

import java.util.List;
import java.util.Map;

import user.bean.UserDTO;
import user.bean.UserDTO2;

public interface UserDAO {

	public List<UserDTO> getUserForm(Map<String, Integer> map);

	public UserDTO getUserId(int userId);
	
	public UserDTO chkUsername(String username);

	public UserDTO chkEmail(String email);

	public void joinOk(UserDTO userDTO);

	public String findUserOk(String phoneNum);

	public UserDTO chkUserPwd(Map<String, String> map);
	
	public void loginOk(String username);
	
	public UserDTO findPwdOk(Map<String, String> map);

	public void updatePwd(Map<String, String> map);

	public UserDTO chkAccessToken(String access_Token);

	public void joinSocialOk(UserDTO userDTO);

	public void kakaoLoginOk(String email);
	
	public void updateUsername(UserDTO userDTO);

	public void updateEmail(UserDTO userDTO);

	public void updateFullName(UserDTO userDTO);

	public void updatePhoneNum(UserDTO userDTO);

	public void ratingChange( Map<String, Object> map);

	public List<UserDTO2> getTradeForm(Map<String, Integer> map);

	public UserDTO getAdminUserId(String userId);

	
}
