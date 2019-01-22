package cn.itrip.service.hotelorder;

import cn.itrip.beans.pojo.ItripHotelOrder;
import cn.itrip.beans.vo.order.ItripPersonalOrderRoomVO;
import cn.itrip.common.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItripHotelOrderService {
    public List<ItripHotelOrder> itripHotelOrderInfo(Map<String,Object> param) throws Exception;

    public Integer itripHotelOrderStore(Map<String,Object> param);

    public List<ItripHotelOrder> itripHotelOrderList(Map<String,Object> param) throws Exception;

    public List<ItripHotelOrder> itripHotelOrderInsert(Map<String,Object> param) throws Exception;

    public List<ItripHotelOrder> itripHotelOrderId(Integer id) throws Exception;

    public ItripPersonalOrderRoomVO getItripOrdergeren(@Param(value = "hotelid")Long hotelid) throws Exception;

    public Integer itripCount(Map<String,Object> param)throws Exception;

    public Page<ItripHotelOrder> queryItripLabelDicPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize)throws Exception;

}

