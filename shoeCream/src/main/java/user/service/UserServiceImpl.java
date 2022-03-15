package user.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import user.bean.UserDTO;
import user.bean.UserDTO2;
import user.dao.UserDAO;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private HttpSession session;

	@Override
	public List<UserDTO> getUserForm(String pg) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("pg", Integer.parseInt(pg));

		List<UserDTO> list = userDAO.getUserForm(map);
		System.out.println("userService list: " + list);
		return list;
	}

	/*@Override
	public UserDTO getUserId(int userId) {
		return userDAO.getUserId(userId);
	} */

	@Override
	public String chkUsername(String username) {
		UserDTO userDTO = userDAO.chkUsername(username);

		if (userDTO == null)
			return "not_exist";
		else
			return "exist";
	}

	@Override
	public String chkEmail(String email) {
		UserDTO userDTO = userDAO.chkEmail(email);

		if (userDTO == null)
			return "not_exist";
		else
			return "exist";
	}

	@Override
	public void joinOk(UserDTO userDTO) {
		userDAO.joinOk(userDTO);
	}

	@Override
	public String loginOk(Map<String, String> map) {
		// 아이디, 비밀번호 확인
		UserDTO userDTO = userDAO.chkUserPwd(map);

		if (userDTO == null)
			return "fail";
		else {
			session.setAttribute("ssUserId", userDTO.getUserId());
			session.setAttribute("ssUsername", userDTO.getUsername());
			// 로그인 성공, 최종접속일 수정
			userDAO.loginOk(userDTO.getUsername());

			return "success";
		}
	}

	@Override
	public void logout() {
		session.invalidate();
	}

	@Override
	public String findUserOk(String phoneNum) {
		String username = userDAO.findUserOk(phoneNum);
		System.out.println(username);

		if (username == null)
			return "not_exist";
		else
			return username;
	}

	@Override
	public String findPwdOk(Map<String, String> map) {
		UserDTO userDTO = userDAO.findPwdOk(map);

		if (userDTO == null)
			return "not_exist";
		else
			return "exist";
	}

	/* 비밀번호 찾기 */
	// 임시 비밀번호 생성
	public static String tempPassword(int leng) {
		int index = 0;
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a',
				'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
				'w', 'x', 'y', 'z' }; // 배열 안의 문자, 숫자는 원하는 대로

		StringBuffer password = new StringBuffer();
		Random random = new Random();

		for (int i = 0; i < leng; i++) {
			double rd = random.nextDouble();
			index = (int) (charSet.length * rd);

			password.append(charSet[index]);

			System.out.println("index::" + index + "	charSet::" + charSet[index]);
		}

		return password.toString();
		// StringBuffer를 String으로 변환해서 return하려면 toString()을 사용하면 된다.
	}

	// SMS 발송
	@Override
	public Map<String, String> sendSMS(String phoneNum) {
		String api_key = "NCSEINWFODTYVXTL";
		String api_secret = "HMRHSZRIH9DVPPROJ8MKQ4AKXNP03GLN";
		Message coolsms = new Message(api_key, api_secret);

		String pwd = tempPassword(10);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", phoneNum); // 수신번호
		params.put("from", "01048611073"); // 발신번호
		params.put("text", "[SHOE-CREAM] 임시 비밀번호 [" + pwd + "] 입니다."); // 문자내용
		params.put("type", "SMS"); // 문자 타입
		params.put("app_version", "test app 1.2");

		Map<String, String> map = new HashMap<String, String>();
		map.put("pwd", pwd);

		try {
			JSONObject obj = coolsms.send(params);
			System.out.println(obj.toString());
			map.put("result", "success");
			return map;

		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
			map.put("result", "fail");
			return map;
		}
	}

	@Override
	public void updatePwd(Map<String, String> map) {
		userDAO.updatePwd(map);
	}

	/* 이메일 인증 */
	@Override
	public int authEmail(String email) throws Exception {
		// 인증번호(난수) 생성
		Random random = new Random();
		int authNum = random.nextInt(888888) + 111111;

		// 메일 전송
		MailUtils sendMail = new MailUtils(mailSender);

		sendMail.setSubject("[SHOE-CREAM] 회원가입 이메일 인증");
		sendMail.setText(new StringBuffer().append("<h1>[이메일 인증 코드]</h1>").append("<p>안녕하세요. SHOE-CREAM입니다.</p>")
				.append("<p>아래의 인증번호를 입력하시면 이메일 인증이 완료됩니다.</p>").append("<a")
				.append("' target='_blenk'>" + authNum + "</a>").toString());
		sendMail.setFrom("username", "password");
		sendMail.setTo(email);
		sendMail.send();

		return authNum;
	}

	/* 카카오 로그인 */
	// access_Token 요청
	public String getAccessToken(String authorize_code) {
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqURL);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로

			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");

			sb.append("&client_id=8f0cf6601a7a42e678ddd67614c593ab"); // 본인이 발급받은 key
			sb.append("&redirect_uri=http://localhost:8090/shoeCream/user/kakaoLogin"); // 본인이 설정한 주소
			sb.append("&code=" + authorize_code);
			bw.write(sb.toString());
			bw.flush();

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			// Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			access_Token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

			System.out.println("access_token : " + access_Token);
			System.out.println("refresh_token : " + refresh_Token);

			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return access_Token;
	}

	// 사용자 정보 요청
	@Override
	public HashMap<String, Object> getUserInfo(String access_Token) {
		// 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
		HashMap<String, Object> userInfo = new HashMap<String, Object>();
		// url로 request하여 userInfo를 가져올 것
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			// 요청에 필요한 Header에 포함될 내용
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			JsonElement id = element.getAsJsonObject().get("id");
			System.out.println(id);

			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

			String nickname = properties.getAsJsonObject().get("nickname").getAsString();
			String profile_image = properties.getAsJsonObject().get("profile_image").getAsString();
			String email = kakao_account.getAsJsonObject().get("email").getAsString();

			userInfo.put("nickname", nickname);
			userInfo.put("profile_image", profile_image);
			userInfo.put("email", email);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return userInfo;
	}

	@Override
	public void kakaoLogout(String access_Token) {
		String reqURL = "https://kapi.kakao.com/v1/user/logout";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String result = "";
			String line = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		session.removeAttribute("ssUserId");
		session.removeAttribute("ssEmail");
		session.removeAttribute("ssAccessToken");
	}

	@Override
	public void joinSocialOk(UserDTO userDTO) {
		userDAO.joinSocialOk(userDTO);

		// 세션에 사용자 정보 저장
		userDTO = userDAO.chkEmail(userDTO.getEmail());
		session.setAttribute("ssUserId", userDTO.getUserId());
		session.setAttribute("ssUsername", userDTO.getUsername());
		session.setAttribute("ssAccessToken", userDTO.getAccessToken());
		
	}

	@Override
	public void kakaoLoginOk(String email, String access_Token) {
		UserDTO userDTO = userDAO.chkEmail(email);
		// 세션에 사용자 정보 저장
		session.setAttribute("ssUserId", userDTO.getUserId());
		session.setAttribute("ssUsername", userDTO.getUsername());
		session.setAttribute("ssAccessToken", access_Token);
		
		userDAO.kakaoLoginOk(email);
	}

	/* 휴대폰 본인인증 */
	@Override
	public Map<String, String> authPhonNum(String phoneNum) {
		String api_key = "NCSEINWFODTYVXTL";
		String api_secret = "HMRHSZRIH9DVPPROJ8MKQ4AKXNP03GLN";
		Message coolsms = new Message(api_key, api_secret);

		int randomNumber = (int) ((Math.random() * (9999 - 1000 + 1)) + 1000);// 난수 생성
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", phoneNum); // 수신번호
		params.put("from", "01048611073"); // 발신번호
		params.put("text", "[SHOE-CREAM] 인증번호는 [" + randomNumber + "] 입니다."); // 문자내용
		params.put("type", "SMS"); // 문자 타입
		params.put("app_version", "test app 1.2");

		Map<String, String> map = new HashMap<String, String>();
		map.put("randomNumber", randomNumber + "");

		try {
			JSONObject obj = coolsms.send(params);
			System.out.println(obj.toString());
			map.put("result", "success");
			return map;

		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
			map.put("result", "fail");
			return map;
		}
	}

	@Override
	public UserDTO getUserId(int userId) {
		return userDAO.getUserId(userId);
	}


	@Override
	public List<UserDTO2> getTradeForm(String pg) {
		Map<String, Integer> map = new HashMap<String, Integer>(); 
		map.put("endNum", Integer.parseInt(pg) * 5);
		map.put("startNum", (Integer.parseInt(pg) * 5) - 4);
		
		List<UserDTO2> list = userDAO.getTradeForm(map);
		System.out.println("userService list: "+list);
		System.out.println("map"+map);
		return list;
	}


	@Override
	public UserDTO getAdminUserId(String userId) {
		return userDAO.getAdminUserId(userId);
	}

	@Override
	public void ratingChange( Map<String, Object> map) {
		userDAO.ratingChange(map);
	}
}
