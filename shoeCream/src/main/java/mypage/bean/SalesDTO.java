package mypage.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Component
public class SalesDTO {
	private int saleId;
	private int userId;
	private int productId;
	private int price;
	private int status;
	private int productSize;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date dueDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date regDate;
	
	// 리스트
	private String img1;
	private String productName;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date tradeDate;
	private int checkStatus;
	private int checkResult;
	private int deliveryStatus;
}
