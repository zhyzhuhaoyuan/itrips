package cn.itrip.dao.hotelorder;

import cn.itrip.beans.pojo.ItripHotelOrder;
import cn.itrip.beans.vo.order.ItripPersonalOrderRoomVO;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItripHotelOrderMapper {
    public List<ItripHotelOrder> itripHotelOrderInfo(Map<String,Object> param) throws Exception;

    public Integer itripHotelOrderStore(Map<String,Object> param);

    public List<ItripHotelOrder> itripHotelOrderList(Map<String,Object> param) throws Exception;

    public List<ItripHotelOrder> itripHotelOrderInsert(Map<String,Object> param) throws Exception;

    public List<ItripHotelOrder> itripHotelOrderId(@Param(value = "id")Integer id) throws Exception;

    public ItripPersonalOrderRoomVO getItripOrdergeren(@Param(value = "id")Integer id) throws Exception;

    public Integer itripCount(Map<String,Object> param)throws Exception;

}
