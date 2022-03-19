package shop.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter @Setter @ToString
public class ShopDTO {
	//product
	private int productId;
	private String productName;
	private String productNameKor;
	private String modelId;
	private int releasePrice;
	private String img1;
	private String img2;
	private String img3;
	@JsonFormat(pattern="yyyy/MM/dd")
	private Date releaseDate;
	private String brandname;
	private int productSize;
	private String productCollection;
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
	private Date productRegDate;
	//styleCount
	private int styleCount;
	//wishListCount
	private int wishListCount;
	//price
	private int todaysPrice;
	
}
