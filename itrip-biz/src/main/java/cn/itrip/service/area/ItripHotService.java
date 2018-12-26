package cn.itrip.service.area;

import cn.itrip.beans.pojo.ItripAreaDic;
import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.beans.pojo.ItripHotel;

import java.util.List;

public interface ItripHotService {
    public List<ItripAreaDic> getItripHotById(Integer isChina)throws Exception;

    public List<ItripAreaDic> getItripHotByIdsq(Long parent) throws Exception;

    public ItripHotel getItripHotelIdsp(Integer Id) throws  Exception;

    public List<String> getItripHotByName(Integer hotelId)throws Exception;

    public ItripHotel getItripHotelfacilities(Integer provinceId)throws Exception;

    public ItripHotel getItripHotelPolicy(Integer provinceId)throws Exception;

}