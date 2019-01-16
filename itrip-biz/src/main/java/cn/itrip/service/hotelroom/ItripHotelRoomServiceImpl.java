package cn.itrip.service.hotelroom;

import cn.itrip.beans.vo.hotelroom.ItripHotelRoomVO;
import cn.itrip.dao.hotelroom.HotelroomMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ItripHotelRoomServiceImpl implements ItripHotelRoomService {

    @Resource
    public HotelroomMapper hotelroomMapper;

    @Override
    public List<ItripHotelRoomVO> getItripHotelRommid(Map<String,Object> param) throws Exception {
        return hotelroomMapper.getItripHotelRommid(param);
    }

}
