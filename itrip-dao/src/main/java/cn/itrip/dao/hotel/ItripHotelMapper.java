package cn.itrip.dao.hotel;

import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.pojo.ItripHotelRoom;

import java.util.List;

public interface ItripHotelMapper {
    public List<ItripHotel> getItripHotelId(Integer cityId) throws Exception;



}
