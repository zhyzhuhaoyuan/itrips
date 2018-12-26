package cn.solr.dao.impl;

import cn.itrip.beans.pojo.ItripComment;
import cn.solr.dao.BaseDao;
import cn.solr.dao.HotelDao;
import cn.solr.entity.Hotel;
import cn.solr.entity.ItripHotelVO;
import org.apache.solr.client.solrj.SolrQuery;

import java.util.List;

public class HotelDaoImpl implements HotelDao {
    public static String url = "http://localhost:8002/solr/hotel/";

    private BaseDao<Hotel> hotelBaseDao = new BaseDao<Hotel>(url);
    private BaseDao<ItripHotelVO> hotelcomment = new BaseDao<ItripHotelVO>(url);

    public List<Hotel> queryHotelList(String keyword) {
        SolrQuery solrQuery = new SolrQuery("keyword:" + keyword);
        List<Hotel> list = hotelBaseDao.queryList(solrQuery, Hotel.class);
        return list;
    }

    @Override
    public List<ItripHotelVO> searchItripHotelByCity(String keyword, Integer rows) {
        SolrQuery solrQuery = new SolrQuery("cityId:" + keyword);
        solrQuery.setRows(rows);
        List<ItripHotelVO> list=hotelcomment.queryList(solrQuery, ItripHotelVO.class);
        return list;
    }
}
