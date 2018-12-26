package cn.solr.service;

import cn.itrip.beans.pojo.ItripComment;
import cn.solr.entity.ItripHotelVO;

import java.util.List;
import java.util.Map;

public interface SearchHotelService {
    public List<ItripHotelVO> searchItripHotelByCity(String keyword , Integer rows);
}
