package address.dao;

import java.util.List;
import java.util.Map;

import address.bean.AddressDTO;

public interface AddressDAO {

	public List<AddressDTO> getAddressList(Map<String, Integer> map);

}
