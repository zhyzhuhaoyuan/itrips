package cn.itrip.service.area;

import cn.itrip.beans.pojo.ItripAreaDic;
import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.pojo.ItripHotelRoom;
import cn.itrip.beans.vo.hotelroom.ItripHotelRoomVO;
import cn.itrip.dao.area.ItripHotMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ItripHotServiceImpl implements ItripHotService {
    @Resource
    public ItripHotMapper itripHotMapper;

    public List<ItripAreaDic> getItripHotById(Integer isChina)throws Exception{
        return itripHotMapper.getItripHotById(isChina);
    }

    @Override
    public List<ItripAreaDic> getItripHotByIdsq(Long parent) throws Exception {
        return itripHotMapper.getItripHotByIdsq(parent);
    }

    @Override
    public ItripHotel getItripHotelIdsp(Integer id) throws Exception {
        return itripHotMapper.getItripHotelIdsp(id);
    }

    @Override
    public List<String> getItripHotByName(Integer hotelId) throws Exception {
        return itripHotMapper.getItripHotByName(hotelId);
    }

    @Override
    public ItripHotel getItripHotelfacilities(Integer id) throws Exception {
        return itripHotMapper.getItripHotelfacilities(id);
    }

    @Override
    public ItripHotel getItripHotelPolicy(Integer id) throws Exception {
        return itripHotMapper.getItripHotelPolicy(id);
    }


}
