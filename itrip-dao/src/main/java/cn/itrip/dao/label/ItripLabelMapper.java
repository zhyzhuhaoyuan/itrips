package cn.itrip.dao.label;

import cn.itrip.beans.pojo.ItripLabelDic;
import cn.itrip.beans.vo.hotel.ItripSearchDetailsHotelVO;


import java.util.List;

public interface ItripLabelMapper {
    public List<ItripLabelDic> getItripLabel()throws Exception;

    public List<String> getItripLabelName(Integer cityId) throws  Exception;

    public List<ItripSearchDetailsHotelVO> getItripLabelHotel(Integer cityId) throws  Exception;

    public List<ItripLabelDic> getItripRoomType() throws Exception;

}
