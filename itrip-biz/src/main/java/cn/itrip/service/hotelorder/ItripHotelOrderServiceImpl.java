package cn.itrip.service.hotelorder;

import cn.itrip.beans.pojo.ItripHotelOrder;
import cn.itrip.dao.hotelorder.ItripHotelOrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ItripHotelOrderServiceImpl implements ItripHotelOrderService{
    @Resource
    public ItripHotelOrderMapper itripHotelOrderMapper;

    @Override
    public List<ItripHotelOrder> itripHotelOrderInfo(Map<String, Object> param) throws Exception {
        return itripHotelOrderMapper.itripHotelOrderInfo(param);
    }

    @Override
    public Integer itripHotelOrderStore(Map<String, Object> param) {
        return itripHotelOrderMapper.itripHotelOrderStore(param);
    }
}