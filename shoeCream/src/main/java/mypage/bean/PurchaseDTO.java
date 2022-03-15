package mypage.bean;


import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Component
public class PurchaseDTO {
	private int purchaseId;
	private int userId;
	private int productId;
	private int status;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date regDate;
	
	// 리스트뽑기용
	private int productSize;
	private String productName;
	private String img1;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date tradeDate;
	private int productPrice;
	
}
