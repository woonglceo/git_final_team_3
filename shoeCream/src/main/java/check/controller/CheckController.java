package check.controller;


import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import check.bean.CheckDTO;
import check.bean.CheckPaging;
import check.service.CheckService;
import user.bean.UserPaging;


@Controller
@RequestMapping(value = "/adminViews/check")
public class CheckController {

	@Autowired
	private CheckService checkService;
	
	// 검수목록 기본
	@GetMapping(value="checkForm")
	public String checkForm(@RequestParam(required = false, defaultValue = "1") String pg, Model model) {
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/WEB-INF/adminViews/check/checkForm.jsp");
		return "/admin/adminIndex";
	}
	
	// 데이터 넣어서 검수목록 전체 조회
	@PostMapping(value="getCheckForm")
	@ResponseBody
	public Map<String, Object> getCheckForm(@RequestParam(required=false, defaultValue="1") String pg) {
		Map<String, Object> map = new HashedMap<String, Object>();				
		List<CheckDTO> list = checkService.getCheckForm(pg);
		CheckPaging checkPaging = checkService.checkPaging(pg);

		map.put("checkPaging", checkPaging);
		map.put("list", list);
		
		return map;
	}
	
	// 필터 검색
	@PostMapping(value="searchBtnForm")
	@ResponseBody
	public List<Object> searchBtnForm(@RequestParam Map<String, Object> map) {
		List<Object> list =  checkService.searchBtnForm(map);
		
		System.out.println("list"+list);
		return list;
	}
}
