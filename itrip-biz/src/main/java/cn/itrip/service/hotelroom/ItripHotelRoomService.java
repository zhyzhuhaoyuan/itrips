package cn.itrip.service.hotelroom;

import cn.itrip.beans.vo.hotelroom.ItripHotelRoomVO;

import java.util.List;
import java.util.Map;

/**
* Created by shang-pc on 2015/11/7.
*/
public interface ItripHotelRoomService {

    public List<ItripHotelRoomVO> getItripHotelRommid(Map<String,Object> param) throws Exception;
}
