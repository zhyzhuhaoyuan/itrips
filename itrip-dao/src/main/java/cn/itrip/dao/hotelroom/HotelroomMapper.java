package cn.itrip.dao.hotelroom;

import cn.itrip.beans.vo.hotelroom.ItripHotelRoomVO;

import java.util.List;
import java.util.Map;

public interface HotelroomMapper {
    public List<ItripHotelRoomVO> getItripHotelRommid(Map<String,Object> param) throws Exception;
}
