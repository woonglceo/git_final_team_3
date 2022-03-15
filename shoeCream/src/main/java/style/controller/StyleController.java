package style.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import style.bean.StyleCardDTO;
import style.service.StyleService;

@Controller
@RequestMapping(value="/style")
public class StyleController {
	@Autowired
	private StyleService styleService;
	@Autowired
	private HttpSession session;
	
	// 페이지) 스타일 메뉴 메인화면 (인기순 목록)
	@RequestMapping(value="/trending")
	public String trending(Model model) {
		model.addAttribute("display", "/WEB-INF/views/style/styleList.jsp");
		return "/index";
	}
	
	// Data) 인기순 목록
	@RequestMapping(value="/getPopularList")
	@ResponseBody
	public List<StyleCardDTO> getPopularList() { 
		return styleService.getPopularList();
	}
	
	// 페이지) 최신순 목록
	@RequestMapping(value="/newest")
	public String newest(Model model) {
		model.addAttribute("display", "/WEB-INF/views/style/styleListRecent.jsp");
		return "/index";
	}
	
	// Data) 최신순 목록
	@RequestMapping(value="/getRecentList")
	@ResponseBody
	public List<StyleCardDTO> getRecentList() { 
		return styleService.getRecentList();
	}
	
	// 페이지) 선택 글 상세
	@RequestMapping(value="/details")
	public String details(Model model) {
		model.addAttribute("display", "/WEB-INF/views/style/styleView.jsp");
		return "/index";
	}

	// Data) 선택 글, 댓글 리스트
	@RequestMapping(value="/getDeatilsReplyList")
	@ResponseBody
	public StyleCardDTO getDeatilsReplyList(@RequestParam int styleId) {
		return styleService.getDeatilsReplyList(styleId);
	}
	
	
	// 페이지) 회원 피드
	@RequestMapping(value="/user")
	public String userFeed(Model model) {
		model.addAttribute("display", "/WEB-INF/views/style/styleMyList.jsp");
		return "/index";
	}
	
	// Data) 회원 피드
	@RequestMapping(value="/getUserFeed")
	@ResponseBody
	public Map<String, Object> getUserFeed(@RequestParam(required=false) String username) { 
		if(username == null) {
			username = String.valueOf(session.getAttribute("ssUsername")); 
		}
		return styleService.getUserFeed(username);
	}
	
	//좋아요(공감) 클릭 반영/취소 
	@ResponseBody
	@RequestMapping(value="/switchLike")
	public String switchLike(int StyleId) {
		return styleService.switchLike(StyleId);
	}
	
}
