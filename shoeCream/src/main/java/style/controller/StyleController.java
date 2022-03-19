package style.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import style.bean.StyleBoardDTO;
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
	public String details(@RequestParam int styleId, Model model) {
		model.addAttribute("styleId", styleId);
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
	public String userFeed(@RequestParam(required=false) String username, Model model) {
		if(username == null) {
			username = String.valueOf(session.getAttribute("ssUsername")); 
		}
		model.addAttribute("username", username);
		model.addAttribute("display", "/WEB-INF/views/style/styleUserFeed.jsp");
		return "/index";
	}
	
	// Data) 회원 피드
	@RequestMapping(value="/getUserFeed")
	@ResponseBody
	public Map<String, Object> getUserFeed(@RequestParam(required=false) String username) { 
		return styleService.getUserFeed(username);
	}
	
	//좋아요(공감) 클릭 반영/취소 
	@ResponseBody
	@RequestMapping(value="/switchLike")
	public String switchLike(@RequestParam String styleId) {
		return styleService.switchLike(Integer.parseInt(styleId));
	}
	
	//게시글에 공감한 회원 목록
	@ResponseBody
	@RequestMapping(value="/getLikeUserList")
	public Map<String, Object> getLikeUserList(@RequestParam int styleId) {
		return styleService.getLikeUserList(styleId);
	}

	//게시글 등록
	@ResponseBody
	@RequestMapping(value="/styleWrite")
	public void styleWrite(@ModelAttribute StyleBoardDTO styleBoardDTO, @RequestParam MultipartFile croppedImg) { 
		styleService.styleWrite(styleBoardDTO, croppedImg); //userId, contents, productId만 넘어올 예정
	}
	
	//댓글 등록
	@ResponseBody
	@RequestMapping(value="/replyWrite")
	public void replyWrite(@RequestParam Map<String, Object> map) {
		styleService.replyWrite(map); //styleId, contents
	}
	
	//댓글 수정
	@ResponseBody
	@RequestMapping(value="/replyModify")
	public void replyModify(@RequestParam Map<String, Object> map) {
		styleService.replyModify(map); //styleReplyId, contents
	}
	
	//댓글 삭제
	@ResponseBody
	@RequestMapping(value="/replyDelete")
	public void replyDelete(@RequestParam Map<String, Object> map) {
		styleService.replyDelete(map); //styleReplyId, styleId
	}
}
