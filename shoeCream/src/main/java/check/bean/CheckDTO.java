package check.bean;

import org.springframework.stereotype.Component;
import lombok.Data;

@Component
@Data
public class CheckDTO {
	
	private int checkId;
	private int productId;
	private int userId;
	private int checkState;
	private int checkResult;
	
}
