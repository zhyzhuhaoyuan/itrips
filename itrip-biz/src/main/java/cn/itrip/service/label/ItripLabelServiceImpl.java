package cn.itrip.service.label;

import cn.itrip.beans.pojo.ItripLabelDic;
import cn.itrip.beans.vo.hotel.ItripSearchDetailsHotelVO;
import cn.itrip.dao.label.ItripLabelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItripLabelServiceImpl implements ItripLabelService {
    @Resource
    public ItripLabelMapper itripLabelMapper;

    public List<ItripLabelDic> getItripLabel()throws Exception{
        return itripLabelMapper.getItripLabel();
    }

    @Override
    public List<String> getItripLabelName(Integer cityId) throws Exception {
        return itripLabelMapper.getItripLabelName(cityId);
    }

    @Override
    public List<ItripSearchDetailsHotelVO> getItripLabelHotel(Integer cityId) throws Exception {
        return itripLabelMapper.getItripLabelHotel(cityId);
    }

    @Override
    public List<ItripLabelDic> getItripRoomType() throws Exception {
        return itripLabelMapper.getItripRoomType();
    }
}
