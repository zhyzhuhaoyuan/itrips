package cn.itrip.dao.area;

import cn.itrip.beans.pojo.ItripAreaDic;
import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.beans.pojo.ItripHotel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItripHotMapper {
    public List<ItripAreaDic> getItripHotById(@Param(value = "isChina") Integer isChina)throws Exception;

    public List<ItripAreaDic> getItripHotByIdsq(@Param(value = "parent") Long parent) throws Exception;

    public ItripHotel getItripHotelIdsp(Integer Id) throws  Exception;

    public List<String> getItripHotByName(@Param(value = "hotelId") Integer hotelId)throws Exception;

    public ItripHotel getItripHotelfacilities(@Param(value = "provinceId") Integer provinceId)throws Exception;

    public ItripHotel getItripHotelPolicy(@Param(value = "provinceId") Integer provinceId)throws Exception;
}
