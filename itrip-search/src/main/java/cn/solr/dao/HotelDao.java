package cn.solr.dao;

import cn.itrip.beans.pojo.ItripComment;
import cn.solr.entity.Hotel;
import cn.solr.entity.ItripHotelVO;

import java.util.List;

public interface HotelDao {

    public List<Hotel> queryHotelList(String keyword);

    public List<ItripHotelVO> searchItripHotelByCity(String keyword , Integer rows);
}
