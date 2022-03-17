package mypage.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import account.bean.AccountDTO;
import account.dao.AccountDAO;
import address.bean.AddressDTO;
import address.dao.AddressDAO;
import mypage.bean.MypagePaging;
import mypage.bean.PurchaseDTO;
import mypage.bean.SalesDTO;
import mypage.bean.WishListDTO;
import mypage.dao.MypageDAO;
import product.bean.ProductDTO;
import product.dao.ProductDAO;
import style.dao.StyleDAO;
import user.bean.UserDTO;
import user.dao.UserDAO;

@Service
public class MypageServiceImpl implements MypageService {
	@Autowired
	private MypageDAO mypageDAO;
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private ProductDTO productDTO;
	@Autowired
	private StyleDAO styleDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private AccountDAO accountDAO;
	@Autowired
	private AddressDAO addressDAO;
	@Autowired
	private MypagePaging mypagePaging;
	@Autowired
	private HttpSession session;
	@Autowired
	private SalesDTO salesDTO;

	// 관심 상품 불러오기
	@Override
	public List<Map<String, Object>> getWishList(String pg, int userId) {
		// 해당 회원의 관심상품 목록 가져오기(최신 순으로 페이징 처리)
		Map<String, Integer> map = this.getPageRange(pg);
		map.put("userId", userId);
		List<WishListDTO> list = mypageDAO.getWishList(map);

		if (list == null)
			return null;
		else {
			// 페이지에 뿌릴 데이터 담기
			List<Map<String, Object>> wishList = new ArrayList<Map<String, Object>>();
			Map<String, Object> wishProduct;
			for (WishListDTO wish : list) {
				wishProduct = new HashMap<String, Object>();
				wishProduct.put("wishListId", wish.getWishListId());
				int productId = wish.getProductId();
				productDTO = productDAO.getProductById(productId);
				wishProduct.put("productId", productId);
				wishProduct.put("img", productDTO.getImg1());
				wishProduct.put("brand", productDTO.getBrandName());
				wishProduct.put("productName", productDTO.getProductName());

				Integer price = styleDAO.getLowestPriceByProductId(productId); // 판매 테이블에서 최저 가격 1개 추출 (조건)상품번호, 판매상태=0,
																				// 가격 오름차순
				if (price == null)
					wishProduct.put("price", 0);
				else
					wishProduct.put("price", price);

				wishList.add(wishProduct);
			}
			return wishList;
		}
	}

	// 관심상품 삭제
	@Override
	public void deleteWish(int wishListId) {
		mypageDAO.deleteWish(wishListId);
	}
	
	@Override
	public String switchWish(int productId) {
		//로그인 여부 확인 
		if(session.getAttribute("ssUserId") == null) {
			return "non-login";
		} else {
			//로그인 된 상태일 경우
			int userId = (int) session.getAttribute("ssUserId");
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("productId", productId);
			map.put("userId", userId);
			
			if(mypageDAO.getWishOnOff(map) == null) { //위시리스트에 dto 없을 경우 위시 추가
				mypageDAO.addWish(map); //wish_list 테이블 데이터 insert
				return "on";
				
			} else { //공감 내역 있을 경우 공감 취소
				int wishListId = mypageDAO.getWishOnOff(map).getWishListId();
				mypageDAO.deleteWish(wishListId); //wish_list 테이블 데이터 delete
				return "off";
			}
		}
	}

	// 페이징 객체 생성
	@Override
	public MypagePaging paging(String pg, String tableName, int userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", tableName);
		map.put("userId", userId);
		int totalA = mypageDAO.getTotalDB(map);
		mypagePaging.setCurrentPage(Integer.parseInt(pg)); // 현재 페이지
		mypagePaging.setPageBlock(5);
		mypagePaging.setPageSize(5);
		mypagePaging.setTotalA(totalA);
		mypagePaging.makePagingHTML();

		return mypagePaging;
	}

	// 페이지 글 범위 생성
	private Map<String, Integer> getPageRange(String pg) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		int pgNum = Integer.parseInt(pg);
		int qtyInPage = 5; // 한 페이지에 보여줄 갯수
		int endNum = pgNum * qtyInPage; // 선택 페이지의 끝 글번호
		int startNum = endNum - (qtyInPage - 1); // 선택 페이지의 시작 글번호

		map.put("startNum", startNum);
		map.put("endNum", endNum);
		return map;
	}

	// 구매내역 불러오기
	@Override
	public List<Map<String, Object>> getBuyList(String pg, int userId) {
		Map<String, Integer> map = this.getPageRange(pg);
		map.put("userId", userId);
		List<PurchaseDTO> list = mypageDAO.getBuyList(map);
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yy/MM/dd");
		
		if(list == null) return null;
		else {
			//페이지에 뿌릴 데이터 담기
			List<Map<String, Object>> buyList = new ArrayList<Map<String,Object>>();
			Map<String, Object> buyProduct;
				for(PurchaseDTO purchase : list) {
					buyProduct = new HashMap<String, Object>();
					int productId = purchase.getProductId();
					productDTO = productDAO.getProductById(productId);
					// 상품정보
					buyProduct.put("img", productDTO.getImg1());
					buyProduct.put("productName", productDTO.getProductName());
					// 구매가
					salesDTO = mypageDAO.getPrice(productId);
					buyProduct.put("productPrice", salesDTO.getPrice());
					buyProduct.put("productSize", purchase.getProductSize());
					cal.setTime(purchase.getRegDate());
					cal.add(Calendar.MONTH, 1);
						if(cal.compareTo(cal2) == -1) {
							buyProduct.put("dueDate", "기한만료");
						}else {
							buyProduct.put("dueDate", df.format(cal.getTime()));
						}
					buyList.add(buyProduct);
				}
			return buyList;
		}
	}

	@Override
	public int getTotalBuying(int userId) {
		return mypageDAO.getTotalBuying(userId);
	}
	
	// 개월 수
	@Override
	public List<Map<String, Object>> getMonthBuyingList(Map<String, Object> map) {
		// 페이징
		Map<String, Integer> map2 = this.getPageRange((String) map.get("pg"));	
		map.put("startNum", map2.get("startNum"));
		map.put("endNum", map2.get("endNum"));
		
		List<PurchaseDTO> list = mypageDAO.getMonthBuyingList(map);
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yy/MM/dd");
		
		if(list == null) return null;
		else {
			//페이지에 뿌릴 데이터 담기
			List<Map<String, Object>> buyList = new ArrayList<Map<String,Object>>();
			Map<String, Object> buyProduct;
				for(PurchaseDTO purchase : list) {
					buyProduct = new HashMap<String, Object>();
					int productId = purchase.getProductId();
					productDTO = productDAO.getProductById(productId);
					// 상품정보
					buyProduct.put("img", productDTO.getImg1());
					buyProduct.put("productName", productDTO.getProductName());
					// 구매가
					salesDTO = mypageDAO.getPrice(productId);
					buyProduct.put("productPrice", salesDTO.getPrice());
					buyProduct.put("productSize", purchase.getProductSize());
					cal.setTime(purchase.getRegDate());
					cal.add(Calendar.MONTH, 1);
						if(cal.compareTo(cal2) == -1) {
							buyProduct.put("dueDate", "기한만료");
						}else {
							buyProduct.put("dueDate", df.format(cal.getTime()));
						}
					buyList.add(buyProduct);
				}
			return buyList;
		}
	}
	
	// 달력
	@Override
	public List<Map<String, Object>> getMonthBuyingList2(Map<String, Object> map) {
		Map<String, Integer> map2 = this.getPageRange((String) map.get("pg"));	
		map.put("startNum", map2.get("startNum"));
		map.put("endNum", map2.get("endNum"));
		
		List<PurchaseDTO> list = mypageDAO.getMonthBuyingList2(map);
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yy/MM/dd");
		
		if(list == null) return null;
		else {
			//페이지에 뿌릴 데이터 담기
			List<Map<String, Object>> buyList = new ArrayList<Map<String,Object>>();
			Map<String, Object> buyProduct;
				for(PurchaseDTO purchase : list) {
					buyProduct = new HashMap<String, Object>();
					int productId = purchase.getProductId();
					productDTO = productDAO.getProductById(productId);
					// 상품정보
					buyProduct.put("img", productDTO.getImg1());
					buyProduct.put("productName", productDTO.getProductName());
					// 구매가
					salesDTO = mypageDAO.getPrice(productId);
					buyProduct.put("productPrice", salesDTO.getPrice());
					buyProduct.put("productSize", purchase.getProductSize());
					cal.setTime(purchase.getRegDate());
					cal.add(Calendar.MONTH, 1);
						if(cal.compareTo(cal2) == -1) {
							buyProduct.put("dueDate", "기한만료");
						}else {
							buyProduct.put("dueDate", df.format(cal.getTime()));
						}
					buyList.add(buyProduct);
				}
			return buyList;
		}
	}
	
	
	@Override
	public MypagePaging monthPaging(Map<String, Object> map) {
		  int totalA = mypageDAO.getTotalMonthBuying((int) map.get("userId"));
		 
	      mypagePaging.setCurrentPage(Integer.parseInt((String) map.get("pg"))); //현재 페이지
	      mypagePaging.setPageBlock(5);
	      mypagePaging.setPageSize(5);
	      mypagePaging.setTotalA(totalA);
	      mypagePaging.makePagingHTML();

	      return mypagePaging;
	}

	@Override
	public MypagePaging monthPaging2(Map<String, Object> map) {
		int totalA = mypageDAO.getTotalMonthBuying2(map);
		 
	      mypagePaging.setCurrentPage(Integer.parseInt((String) map.get("pg"))); //현재 페이지
	      mypagePaging.setPageBlock(5);
	      mypagePaging.setPageSize(5);
	      mypagePaging.setTotalA(totalA);
	      mypagePaging.makePagingHTML();

	      return mypagePaging;
	}
	
	@Override
	public int getEndBuying(int userId) {
		return mypageDAO.getEndBuying(userId);
	}

	@Override
	public List<Map<String, Object>> getIngBuyingList(String pg, int userId) {
		// 사진, 상품명, 사이즈, 상태
		Map<String, Integer> map = this.getPageRange(pg);
		map.put("userId", userId);
		List<PurchaseDTO> list = mypageDAO.getIngBuyingList(map);

		if (list == null)
			return null;
		else {
			// 페이지에 뿌릴 데이터 담기
			List<Map<String, Object>> buyList = new ArrayList<Map<String, Object>>();
			Map<String, Object> buyProduct;
			for (PurchaseDTO purchase : list) {
				if (purchase.getStatus() == 0) {
					buyProduct = new HashMap<String, Object>();
					int productId = purchase.getProductId();
					productDTO = productDAO.getProductById(productId);
					// 상품정보
					buyProduct.put("img", productDTO.getImg1());
					buyProduct.put("productName", productDTO.getProductName());
					buyProduct.put("productSize", purchase.getProductSize());
					// 구매가
					salesDTO = mypageDAO.getPrice(productId);
					buyProduct.put("productPrice", salesDTO.getPrice());

					buyList.add(buyProduct);
				}
			}
			return buyList;
		}
	}

	// 구매내역 페이징
	@Override
	public MypagePaging ingPaging(String pg, int userId) {

		int totalA = this.getTotalIngBuying(userId);
		mypagePaging.setCurrentPage(Integer.parseInt(pg)); // 현재 페이지
		mypagePaging.setPageBlock(5);
		mypagePaging.setPageSize(5);
		mypagePaging.setTotalA(totalA);
		mypagePaging.makePagingHTML();

		return mypagePaging;
	}

	@Override
    public List<Map<String, Object>> getEndBuyingList(String pg, int userId) {
        SimpleDateFormat df = new SimpleDateFormat("yy/MM/dd");
        Map<String, Integer> map = this.getPageRange(pg);
        map.put("userId", userId);
        
        List<PurchaseDTO> list = mypageDAO.getEndBuyingList(map);

        List<Map<String, Object>> buyList = new ArrayList<Map<String,Object>>();
        Map<String, Object> buyProduct;
            for(PurchaseDTO purchase : list) {
                        buyProduct = new HashMap<String, Object>();
                        // 상품정보
                        buyProduct.put("img", purchase.getImg1());
                        buyProduct.put("productName", purchase.getProductName());
                        buyProduct.put("productSize", purchase.getProductSize());						
                        buyProduct.put("productPrice", purchase.getProductPrice());
                        buyProduct.put("tradeDate", df.format(purchase.getTradeDate()));

                        buyList.add(buyProduct);
                }			
        return buyList;
    }

	@Override
	public MypagePaging endPaging(String pg, int userId) {
		int totalA = this.getEndBuying(userId);

		mypagePaging.setCurrentPage(Integer.parseInt(pg)); // 현재 페이지
		mypagePaging.setPageBlock(5);
		mypagePaging.setPageSize(5);
		mypagePaging.setTotalA(totalA);
		mypagePaging.makePagingHTML();

		return mypagePaging;
	}

	/* 마이페이지 내 정보 */
	// 회원 정보 불러오기
	@Override
	public UserDTO getUser(int userId) {
		return userDAO.getUserId(userId);
	}

	@Override
	public void updateUsername(String username) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId((int) session.getAttribute("ssUserId"));
		userDTO.setUsername(username);
		userDAO.updateUsername(userDTO);
	}

	@Override
	public void updateEmail(String email) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId((int) session.getAttribute("ssUserId"));
		userDTO.setEmail(email);
		userDAO.updateEmail(userDTO);
	}

	@Override
	public void updatePwd(Map<String, String> map) {
		userDAO.updatePwd(map);
	}

	@Override
	public void updateFullName(String fullName) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId((int) session.getAttribute("ssUserId"));
		userDTO.setFullName(fullName);
		userDAO.updateFullName(userDTO);
	}

	@Override
	public void updatePhoneNum(String phoneNum) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId((int) session.getAttribute("ssUserId"));
		userDTO.setPhoneNum(phoneNum);
		userDAO.updatePhoneNum(userDTO);
	}

	@Override
	public AccountDTO getAccount(int userId) {
		return accountDAO.getAccountByUserId(userId);
	}

	@Override
	public void registerAccount(AccountDTO accountDTO) {
		int userId = (int) session.getAttribute("ssUserId");
		accountDTO.setUserId(userId);
		accountDAO.registerAccount(accountDTO);
	}

	@Override
	public void updateAccount(AccountDTO accountDTO) {
		int userId = (int) session.getAttribute("ssUserId");
		accountDTO.setUserId(userId);
		accountDAO.updateAccount(accountDTO);

	}

	@Override
	public List<AddressDTO> getAddressList(String pg, int userId) {
		Map<String, Integer> map = this.getPageRange(pg);
		map.put("userId", userId);
		List<AddressDTO> list = addressDAO.getAddressList(map);
		return list;
	}

	@Override
	public int getTotalIngBuying(int userId) {
		return mypageDAO.getTotalIngBuying(userId);
	}

	@Override
	public void registerAddress(AddressDTO addressDTO) {
		int userId = (int) session.getAttribute("ssUserId");
		addressDTO.setUserId(userId);

		if (addressDTO.getDefaultAddr().equals("Y")) {
			// 등록된 기본 배송지가 있는지 확인
			AddressDTO addr = addressDAO.chkDefaultAddr(userId);

			if (addr != null) {
				addressDAO.setDefaultAddrN(userId);
			}
		}
		addressDAO.registerAddress(addressDTO);
	}

	@Override
	public void updateAddress(AddressDTO addressDTO) {
		int userId = (int) session.getAttribute("ssUserId");
		addressDTO.setUserId(userId);
		System.out.println(addressDTO.getDefaultAddr());
		
		if (addressDTO.getDefaultAddr().equals("Y")) {
			// 등록된 기본 배송지가 있는지 확인
			AddressDTO addr = addressDAO.chkDefaultAddr(userId);

			if (addr != null) {
				addressDAO.setDefaultAddrN(userId);
			}
		}
		addressDAO.updateAddress(addressDTO);
	}
  
  @Override
	public List<Map<String, Object>> getMonthEndBuyingList(Map<String, Object> map) {
	   	SimpleDateFormat df = new SimpleDateFormat("yy/MM/dd");
	   	Map<String, Integer> map2 = this.getPageRange((String) map.get("pg"));	
		map.put("startNum", map2.get("startNum"));
		map.put("endNum", map2.get("endNum"));
       
       List<PurchaseDTO> list = mypageDAO.getMonthEndBuyingList(map);

       List<Map<String, Object>> buyList = new ArrayList<Map<String,Object>>();
       Map<String, Object> buyProduct;
           for(PurchaseDTO purchase : list) {
                       buyProduct = new HashMap<String, Object>();
                       // 상품정보
                       buyProduct.put("img", purchase.getImg1());
                       buyProduct.put("productName", purchase.getProductName());
                       buyProduct.put("productSize", purchase.getProductSize());						
                       buyProduct.put("productPrice", purchase.getProductPrice());
                       buyProduct.put("tradeDate", df.format(purchase.getTradeDate()));
                       
                       buyList.add(buyProduct);
               }			
       return buyList;
	}
  
  @Override
	public MypagePaging endMonthPaging(Map<String, Object> map) {
	   int totalA = mypageDAO.getTotalEndMonth(map);
       
       mypagePaging.setCurrentPage(Integer.parseInt( (String) map.get("pg"))); //현재 페이지
       mypagePaging.setPageBlock(5);
       mypagePaging.setPageSize(5);
       mypagePaging.setTotalA(totalA);
       mypagePaging.makePagingHTML();
               
       return mypagePaging;
	}
   
   @Override
	public List<Map<String, Object>> getMonthBuyingList3(Map<String, Object> map) {
	   SimpleDateFormat df = new SimpleDateFormat("yy/MM/dd");
	   	Map<String, Integer> map2 = this.getPageRange((String) map.get("pg"));	
		map.put("startNum", map2.get("startNum"));
		map.put("endNum", map2.get("endNum"));
      
		List<PurchaseDTO> list = mypageDAO.getMonthEndBuyingList3(map);

	      List<Map<String, Object>> buyList = new ArrayList<Map<String,Object>>();
	      Map<String, Object> buyProduct;
	          for(PurchaseDTO purchase : list) {
	                      buyProduct = new HashMap<String, Object>();
	                      // 상품정보
	                      buyProduct.put("img", purchase.getImg1());
	                      buyProduct.put("productName", purchase.getProductName());
	                      buyProduct.put("productSize", purchase.getProductSize());						
	                      buyProduct.put("productPrice", purchase.getProductPrice());
	                      buyProduct.put("tradeDate", df.format(purchase.getTradeDate()));
	                      
	                      System.out.println();
	                      buyList.add(buyProduct);
	              }			
	      return buyList;
	}
   
   @Override
	public MypagePaging monthPaging3(Map<String, Object> map) {
	   int totalA = mypageDAO.getTotalMonthBuying3(map);
       
       mypagePaging.setCurrentPage(Integer.parseInt( (String) map.get("pg"))); //현재 페이지
       mypagePaging.setPageBlock(5);
       mypagePaging.setPageSize(5);
       mypagePaging.setTotalA(totalA);
       mypagePaging.makePagingHTML();
               
       return mypagePaging;
  }
}
