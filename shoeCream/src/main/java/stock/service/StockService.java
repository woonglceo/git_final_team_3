package stock.service;

import java.util.List;

import stock.bean.StockDTO;

public interface StockService {

	public List<StockDTO> getStockForm(String pg);

}
