package cn.itrip.service.label;

import cn.itrip.beans.pojo.ItripLabelDic;
import cn.itrip.beans.vo.hotel.ItripSearchDetailsHotelVO;

import java.util.List;

public interface ItripLabelService {

    public List<ItripLabelDic> getItripLabel()throws Exception;

    public List<String> getItripLabelName(Integer cityId) throws  Exception;

    public List<ItripSearchDetailsHotelVO> getItripLabelHotel(Integer cityId) throws  Exception;

    public List<ItripLabelDic> getItripRoomType() throws Exception;

}
