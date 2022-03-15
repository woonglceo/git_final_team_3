package check.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import check.bean.CheckDTO;
import check.service.CheckService;


@Controller
@RequestMapping(value = "/adminViews/check")
public class CheckController {

	@Autowired
	private CheckService checkService;
	

	@GetMapping(value="checkForm")
	public String checkForm(Model model) {
		model.addAttribute("display", "/WEB-INF/adminViews/check/checkForm.jsp");
		return "/admin/adminIndex";
	}
	

	@PostMapping(value="getCheckForm")
	@ResponseBody
	public List<CheckDTO> getCheckForm(@RequestParam(required=false, defaultValue="1") String pg) {
		System.out.println("pg: "+pg);
		List<CheckDTO> list = checkService.getCheckForm(pg);
		System.out.println("list: "+list);
		return list;
	}
	
	
	@PostMapping(value="searchBtnForm")
	@ResponseBody
	public List<CheckDTO> searchBtnForm(@RequestParam(required=false, defaultValue="1") String pg) {
		System.out.println("pg: "+pg);
		List<CheckDTO> list = checkService.searchBtnForm(pg);
		System.out.println("list: "+list);
		return list;
	}
}
