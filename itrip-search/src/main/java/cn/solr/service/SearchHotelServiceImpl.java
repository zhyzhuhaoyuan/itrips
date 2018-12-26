package cn.solr.service;

import cn.itrip.beans.pojo.ItripComment;
import cn.solr.dao.HotelDao;
import cn.solr.dao.impl.HotelDaoImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchHotelServiceImpl implements SearchHotelService {

    private HotelDao hotelDao = new HotelDaoImpl();

    @Override
    public List<ItripComment> searchItripHotelByCity(String keyword, Integer rows) {
        return hotelDao.searchItripHotelByCity(keyword,rows);
    }
}
