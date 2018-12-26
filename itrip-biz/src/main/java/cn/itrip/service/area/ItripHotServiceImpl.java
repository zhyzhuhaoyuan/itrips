package cn.itrip.service.area;

import cn.itrip.beans.pojo.ItripAreaDic;
import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.dao.area.ItripHotMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    public ItripHotel getItripHotelfacilities(Integer provinceId) throws Exception {
        return itripHotMapper.getItripHotelfacilities(provinceId);
    }

    @Override
    public ItripHotel getItripHotelPolicy(Integer provinceId) throws Exception {
        return itripHotMapper.getItripHotelPolicy(provinceId);
    }

}
