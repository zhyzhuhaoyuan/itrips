package cn.itrip.service.hotelorder;

import cn.itrip.beans.pojo.ItripHotelOrder;

import java.util.List;
import java.util.Map;

public interface ItripHotelOrderService {
    public List<ItripHotelOrder> itripHotelOrderInfo(Map<String,Object> param) throws Exception;

    public Integer itripHotelOrderStore(Map<String,Object> param);

    public List<ItripHotelOrder> itripHotelOrderList(Map<String,Object> param) throws Exception;

    public List<ItripHotelOrder> itripHotelOrderInsert(Map<String,Object> param) throws Exception;

    public List<ItripHotelOrder> itripHotelOrderId(Integer id) throws Exception;

}

