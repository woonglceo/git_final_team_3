package shop.controller;

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

import shop.bean.ShopDTO;
import shop.service.ShopService;

@Controller
@RequestMapping(value="shop")
public class ShopController {
	@Autowired
	private ShopService shopService;
	
	@GetMapping(value="shopList")
	public String shopList(@RequestParam(required=false, defaultValue="1") String pg,
		      				Model model) {
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/WEB-INF/views/shop/shopList.jsp");
		return "/index";
	}
	
	@PostMapping(value="getShopList")
	@ResponseBody
	public Map<String, Object> getShopList(@RequestParam(required=false, defaultValue="1") String pg) {
		List<ShopDTO> list = shopService.getShopList(pg);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("shopPaging", shopService.shopPaging(pg));
		
		return map;
	}
	
	@GetMapping(value="shopView")
	public String shopView(@RequestParam String productId, Model model) {
		model.addAttribute("productId", productId);
		model.addAttribute("display", "/WEB-INF/views/shop/shopView.jsp");
		return "/index";
	}
	
	@PostMapping(value="getShop")
	@ResponseBody
	public ShopDTO getShop(@RequestParam String productId) {
		return shopService.getShop(productId);
	}
	
	@PostMapping(value="getShopSearchList")
	@ResponseBody
	public Map<String, Object> getShopSearchList(@RequestParam Map<String, String> map, Model model) { //searchOption, keyword, pg(id=searchPg)
		System.out.println(map.get("searchOption")+", "+map.get("keyword")+", "+map.get("pg"));
		
		
		Map<String, Object> tempMap = new HashMap<String, Object>();
		tempMap.put("list", shopService.getShopSearchList(map));
		tempMap.put("shopPaging", shopService.shopPaging(map.get("pg")));
		
		return tempMap;
	}
	
}
