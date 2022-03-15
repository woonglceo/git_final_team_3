package user.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import order.bean.OrderDTO;
import user.bean.UserDTO;
import user.bean.UserDTO2;
import user.service.UserService;


@Controller
@RequestMapping(value = "/adminViews/user")
public class AdminUserController {
	@Autowired
	private UserService userService;
	
	// 유저목록
	@GetMapping(value="userForm")
	public String userForm(Model model) {
		model.addAttribute("display", "/WEB-INF/adminViews/user/userForm.jsp");
		return "/admin/adminIndex";
	}
	
	// 유저 상세보기
	@GetMapping(value="userView")
	public String userView(Model model) {
		model.addAttribute("display", "/WEB-INF/adminViews/user/userView.jsp");
		return "/admin/adminIndex";
	}
	
	// 크림하고 즉시거래
	@GetMapping(value = "purchaseForm")
	public String purchaseForm(Model model) {
		model.addAttribute("display", "/WEB-INF/adminViews/user/purchaseForm.jsp");
		return "/admin/adminIndex";
	}
	
	// 회원으로부터의 입찰구매
	@GetMapping(value = "bidPurchaseForm")
	public String bidPurchaseForm(Model model) {
		model.addAttribute("display", "/WEB-INF/adminViews/user/bidPurchaseForm.jsp");
		return "/admin/adminIndex";
	}
	
	// 판매내역
	@GetMapping(value = "sellForm")
	public String sellForm(Model model) {
		model.addAttribute("display", "/WEB-INF/adminViews/user/sellForm.jsp");
		return "/admin/adminIndex";
	}
	
	
	@PostMapping(value="getUserForm")
	@ResponseBody
	public List<UserDTO> getUserForm(@RequestParam(required=false, defaultValue="1") String pg) {
		System.out.println("pg: "+pg);
		List<UserDTO> list = userService.getUserForm(pg);
		System.out.println("list: "+list);
		return list;
	}
	
	/*
	 * // 1개값
	 * 
	 * @PostMapping(value="getUser")
	 * 
	 * @ResponseBody public UserDTO getUser(@RequestParam String UserId) { return
	 * userService.getUser(UserId); }
	 */
	
	
	// ajax에서 getUserId값 넘겨주기 위해 사용
	@PostMapping(value="getAdminUserId")
	@ResponseBody
	public UserDTO getAdminUserId(@RequestParam String userId) {
		return userService.getAdminUserId(userId);
	}
	

	
	@PostMapping(value="getTradeForm")
	@ResponseBody
	public List<UserDTO2> getTradeForm(@RequestParam(required=false, defaultValue="1") String pg) {
		System.out.println("pg: "+pg);
		List<UserDTO2> list = userService.getTradeForm(pg);
		System.out.println("list: "+list);
		
		return list;
	}
	
	
	/*@PostMapping(value="ratingChange")
	@ResponseBody
	public UserDTO ratingChange(@RequestParam String userId, @RequestParam int ratings) {
		System.out.println(ratings);
		return userService.ratingChange(userId,ratings);
	}*/
	
	
	@PostMapping(value="ratingChange")
	@ResponseBody
	public void ratingChange(@RequestParam Map<String, Object> map) {
		

		 userService.ratingChange(map);
		
	
	}	
	
}
