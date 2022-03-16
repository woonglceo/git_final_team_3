package mypage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import account.bean.AccountDTO;
import mypage.service.MypageService;

@Controller
@RequestMapping(value="/my")
public class MypageController {
	@Autowired
	private MypageService mypageService;
	@Autowired
	private HttpSession session;
	
	//관심상품 페이지
	@RequestMapping(value="/wish")
	public String wish(@RequestParam(required=false, defaultValue = "1") String pg, Model model) {
		if(session.getAttribute("ssUserId") == null) return "/WEB-INF/views/login";
		else {
			int userId = (int) session.getAttribute("ssUserId");
			model.addAttribute("wishList", mypageService.getWishList(pg, userId)); 
			model.addAttribute("pg", pg);
			model.addAttribute("paging", mypageService.paging(pg, "wish_list", userId)); 
			model.addAttribute("pageName", "wish");
			model.addAttribute("display", "/WEB-INF/views/wish.jsp");
			return "/WEB-INF/views/mypage"; 
		}
	}
	
	//관심상품 페이지
	@RequestMapping(value="/deleteWish")
	@ResponseBody
	public void deleteWish(int wishListId) {
		mypageService.deleteWish(wishListId);
	}
	
		// 총 구매내역 폼
		@GetMapping(value="/buying")
		public String buying(@RequestParam(required=false, defaultValue = "1") String pg, Model model) {
			int userId = (int) session.getAttribute("ssUserId");
			
			model.addAttribute("totalCount", mypageService.getTotalBuying(userId));
			model.addAttribute("ingCount", mypageService.getTotalIngBuying(userId));	
			model.addAttribute("endCount", mypageService.getEndBuying(userId));
			model.addAttribute("pg", pg);
			model.addAttribute("display2", "/WEB-INF/views/buying1.jsp");
			model.addAttribute("display", "/WEB-INF/views/buying.jsp");
			return "/WEB-INF/views/mypage"; 
		}
		
		// 총 구매내역 가져오기
		@PostMapping(value = "getBuyingList")
		@ResponseBody
		public Map<String, Object> getBuyingList(@RequestParam(required=false, defaultValue = "1") String pg){
			Map<String, Object> map = new HashMap<String, Object>();
			int userId = (int) session.getAttribute("ssUserId");
			
			map.put("buyList", mypageService.getBuyList(pg, userId)); 
			map.put("paging", mypageService.paging(pg, "purchase_table", userId)); 
			return map;
		}

		// 총 거래내역의 기간 내 거래내역
		@PostMapping(value = "getMonthBuyingList")
		@ResponseBody
		public Map<String, Object> getMonthBuyingList(@RequestParam(required=false, defaultValue = "1") String pg, @RequestParam Map<String, Object> map){
			Map<String, Object> map2 = new HashMap<String, Object>();
			int userId = (int) session.getAttribute("ssUserId");
			map.put("userId", userId);
			map.put("pg", pg);
			
			map2.put("monthBuyList", mypageService.getMonthBuyingList(map)); 
			map2.put("paging", mypageService.monthPaging(map)); 
			return map2;
		}
		
		// 달력으로 선택한 날짜 사이의 거래내역
		@PostMapping(value = "getMonthBuyingList2")
		@ResponseBody
		public Map<String, Object> getMonthBuyingList2(@RequestParam(required=false, defaultValue = "1") String pg, @RequestParam Map<String, Object> map){
			Map<String, Object> map2 = new HashMap<String, Object>();
			int userId = (int) session.getAttribute("ssUserId");
			map.put("userId", userId);
			map.put("pg", pg);
			
			map2.put("monthBuyList", mypageService.getMonthBuyingList2(map)); 
			map2.put("paging", mypageService.monthPaging2(map)); 
			return map2;
		}
		
		// 거래중 폼
		@GetMapping(value="/ingBuying")
		public String ingBuying(@RequestParam(required=false, defaultValue = "1") String pg, Model model) {
			int userId = (int) session.getAttribute("ssUserId");
			
			model.addAttribute("totalCount", mypageService.getTotalBuying(userId));
			model.addAttribute("ingCount", mypageService.getTotalIngBuying(userId));
			model.addAttribute("endCount", mypageService.getEndBuying(userId));
			model.addAttribute("pg", pg);		
			
			model.addAttribute("display2", "/WEB-INF/views/buying2.jsp");
			model.addAttribute("display", "/WEB-INF/views/buying.jsp");		
			return "/WEB-INF/views/mypage"; 
		}
		
		// 거래중
		@PostMapping(value="/getIngBuyingList")
		@ResponseBody
		public Map<String, Object> getIngBuyingList(@RequestParam(required=false, defaultValue = "1") String pg) {
			Map<String, Object> map = new HashMap<String, Object>();
			int userId = (int) session.getAttribute("ssUserId");
			
			map.put("buyList", mypageService.getIngBuyingList(pg, userId));
			map.put("paging", mypageService.ingPaging(pg, userId)); 	
			return map; 
		}
		
		// 거래 완료 폼
		@GetMapping(value="/endBuying")
		public String endBuying(@RequestParam(required=false, defaultValue = "1") String pg, Model model) {
			int userId = (int) session.getAttribute("ssUserId");
			
			model.addAttribute("totalCount", mypageService.getTotalBuying(userId));
			model.addAttribute("ingCount", mypageService.getTotalIngBuying(userId));
			model.addAttribute("endCount", mypageService.getEndBuying(userId));
			model.addAttribute("pg", pg);
			
			model.addAttribute("display2", "/WEB-INF/views/buying3.jsp");
			model.addAttribute("display", "/WEB-INF/views/buying.jsp");			
			return "/WEB-INF/views/mypage"; 
		}
		
		// 거래완료
		@PostMapping(value="/getEndBuyingList")
		@ResponseBody
		public Map<String, Object> getEndBuyingList(@RequestParam(required=false, defaultValue = "1") String pg) {
			Map<String, Object> map = new HashMap<String, Object>();
			int userId = (int) session.getAttribute("ssUserId");
			
			map.put("buyList", mypageService.getEndBuyingList(pg, userId));
			System.out.println(map.get("buyList"));
			map.put("paging", mypageService.endPaging(pg, userId)); 
			System.out.println(map.get("paging"));
			return map; 
		}
		
		// 거래완료시 2 / 4 / 6
		@PostMapping(value = "getMonthEndBuyingList")
		@ResponseBody
		public Map<String, Object> getMonthEndBuyingList(@RequestParam(required=false, defaultValue = "1") String pg, @RequestParam Map<String, Object> map){
			Map<String, Object> map2 = new HashMap<String, Object>();
			int userId = (int) session.getAttribute("ssUserId");
			map.put("userId", userId);
			map.put("pg", pg);
			
			map2.put("monthBuyList", mypageService.getMonthEndBuyingList(map)); 
			map2.put("paging", mypageService.endMonthPaging(map)); 
			return map2;
		}
		
		@PostMapping(value = "getMonthBuyingList3")
		@ResponseBody
		public Map<String, Object> getMonthBuyingList3(@RequestParam(required=false, defaultValue = "1") String pg, @RequestParam Map<String, Object> map){
			Map<String, Object> map2 = new HashMap<String, Object>();
			int userId = (int) session.getAttribute("ssUserId");
			map.put("userId", userId);
			map.put("pg", pg);
			
			map2.put("monthBuyList", mypageService.getMonthBuyingList3(map)); 
			map2.put("paging", mypageService.monthPaging3(map)); 
			return map2;
		}
		
		
	// 총 판매 내역
	@GetMapping(value="/selling")
	public String selling(@RequestParam(required=false, defaultValue = "1") String pg, Model model) {
		int userId = (int) session.getAttribute("ssUserId");
		
		model.addAttribute("pg", pg);
		model.addAttribute("display2", "/WEB-INF/views/selling1.jsp");
		model.addAttribute("display", "/WEB-INF/views/selling.jsp");
		return "/WEB-INF/views/mypage";  		
	}
	
	// 판매 거래중
	@GetMapping(value="/ingSelling")
	public String ingSelling(@RequestParam(required=false, defaultValue = "1") String pg, Model model) {
		int userId = (int) session.getAttribute("ssUserId");
	
		model.addAttribute("pg", pg);		
		
		model.addAttribute("display2", "/WEB-INF/views/selling2.jsp");
		model.addAttribute("display", "/WEB-INF/views/selling.jsp");		
		return "/WEB-INF/views/mypage"; 
	}
	
	// 판매완료
	@GetMapping(value="/endSelling")
	public String endSelling(@RequestParam(required=false, defaultValue = "1") String pg, Model model) {
		int userId = (int) session.getAttribute("ssUserId");
	
		model.addAttribute("pg", pg);		
		
		model.addAttribute("display2", "/WEB-INF/views/selling3.jsp");
		model.addAttribute("display", "/WEB-INF/views/selling.jsp");		
		return "/WEB-INF/views/mypage"; 
	}
	
	
	/* 마이페이지 내정보 */
	@RequestMapping(value="myProfile")
	public String myProfile(Model model) {
		if(session.getAttribute("ssUserId") == null) return "/WEB-INF/views/login";
		else {
			int userId = (int) session.getAttribute("ssUserId");
			model.addAttribute("userDTO", mypageService.getUser(userId));
			model.addAttribute("pageName", "myProfile");
			model.addAttribute("display", "/WEB-INF/views/myProfile.jsp");
			return "/WEB-INF/views/mypage";			
		}
	}
	
	@RequestMapping(value="myAddress")
	public String myAddress(@RequestParam(required=false, defaultValue = "1") String pg, Model model) {
		if(session.getAttribute("ssUserId") == null) return "/WEB-INF/views/login";
		else {
			int userId = (int) session.getAttribute("ssUserId");
			model.addAttribute("addressList", mypageService.getAddressList(pg, userId));
			model.addAttribute("pg", pg);
			model.addAttribute("paging", mypageService.paging(pg, "address_table", userId)); 
			model.addAttribute("pageName", "myAddress");
			model.addAttribute("display", "/WEB-INF/views/myAddress.jsp");
			return "/WEB-INF/views/mypage";
		}
	}
	
	@RequestMapping(value="myAccount")
	public String myAccount(Model model) {
		if(session.getAttribute("ssUserId") == null) return "/WEB-INF/views/login";
		else {
			int userId = (int) session.getAttribute("ssUserId");
			model.addAttribute("accountDTO", mypageService.getAccount(userId));
			model.addAttribute("pageName", "myAccount");
			model.addAttribute("display", "/WEB-INF/views/myAccount.jsp");
			return "/WEB-INF/views/mypage";
		}
	}
	
	@RequestMapping(value="myStyle")
	public String myStyle(Model model) {
		if(session.getAttribute("ssUserId") == null) return "/WEB-INF/views/login";
		else {
			model.addAttribute("pageName", "myStyle");
			model.addAttribute("display", "/WEB-INF/views/myStyle.jsp");
			return "/WEB-INF/views/mypage";
		}
	}
	
	@RequestMapping(value="myWithdrawal")
	public String myWithdrawal(Model model) {
		if(session.getAttribute("ssUserId") == null) return "/WEB-INF/views/login";
		else {
			model.addAttribute("pageName", "myWithdrawal");
			model.addAttribute("display", "/WEB-INF/views/myWithdrawal.jsp");
			return "/WEB-INF/views/mypage";
		}
	}
	
	@RequestMapping(value="updateUsername")
	@ResponseBody
	public void updateUsername(@RequestParam String username) {
		mypageService.updateUsername(username);
	}
	
	@RequestMapping(value="updateEmail")
	@ResponseBody
	public void updateEmail(@RequestParam String email) {
		mypageService.updateEmail(email);
	}
	
	@RequestMapping(value="updatePwd")
	@ResponseBody
	public void updatePwd(@RequestParam Map<String, String> map) {
		mypageService.updatePwd(map);
	}
	
	@RequestMapping(value="updateFullName")
	@ResponseBody
	public void updateFullName(@RequestParam String fullName) {
		mypageService.updateFullName(fullName);
	}
	
	@RequestMapping(value="updatePhoneNum")
	@ResponseBody
	public void updatePhoneNum(@RequestParam String phoneNum) {
		mypageService.updatePhoneNum(phoneNum);
		
	}

	@RequestMapping(value="registerAccount")
	@ResponseBody
	public void registerAccount(@ModelAttribute AccountDTO accountDTO) {
		mypageService.registerAccount(accountDTO);
	}
	
	@RequestMapping(value="updateAccount")
	@ResponseBody
	public void updateAccount(@ModelAttribute AccountDTO accountDTO) {
		mypageService.updateAccount(accountDTO);
	}

}
