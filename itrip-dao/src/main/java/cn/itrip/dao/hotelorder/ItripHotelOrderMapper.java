package cn.itrip.dao.hotelorder;

import cn.itrip.beans.pojo.ItripHotelOrder;

import java.util.List;
import java.util.Map;

public interface ItripHotelOrderMapper {
    public List<ItripHotelOrder> itripHotelOrderInfo(Map<String,Object> param) throws Exception;

    public Integer itripHotelOrderStore(Map<String,Object> param);

    public List<ItripHotelOrder> itripHotelOrderList(Map<String,Object> param) throws Exception;

}
