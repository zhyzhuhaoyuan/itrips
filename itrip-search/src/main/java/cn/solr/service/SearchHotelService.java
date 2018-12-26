package cn.solr.service;

import cn.itrip.beans.pojo.ItripComment;

import java.util.List;
import java.util.Map;

public interface SearchHotelService {
    public List<ItripComment> searchItripHotelByCity(String keyword ,Integer rows);
}
