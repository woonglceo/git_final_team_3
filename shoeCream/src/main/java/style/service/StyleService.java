package style.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import style.bean.StyleBoardDTO;
import style.bean.StyleBoardPaging;
import style.bean.StyleCardDTO;

public interface StyleService {

	public List<StyleCardDTO> getPopularList();

	public List<StyleCardDTO> getRecentList();

	public Map<String, Object> getUserFeed(String username);
	
	public List<StyleBoardDTO> getStyleList(String pg, String option);

	public StyleBoardPaging styleBoardPaging(String pg, String option);

	public List<StyleBoardDTO> searchStyleList(Map<String, Object> map);
	
	public StyleBoardPaging searchStyleBoardPaging(Map<String, Object> map);

	public void deleteStyle(String styleId);

	public void deletStyleList(String[] checkBoxArr);

	public String switchLike(int styleId);

	public StyleCardDTO getDeatilsReplyList(int styleId);

	public Map<String, Object> getLikeUserList(int styleId);
	
	public void styleWrite(StyleBoardDTO styleBoardDTO, MultipartFile croppedImg);

	public void replyWrite(Map<String, Object> map);

	public void replyModify(Map<String, Object> map);

	public void replyDelete(Map<String, Object> map);
}
