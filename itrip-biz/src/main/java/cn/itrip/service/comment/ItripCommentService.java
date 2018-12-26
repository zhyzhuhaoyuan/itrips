package cn.itrip.service.comment;

import cn.itrip.beans.pojo.ItripComment;

import java.util.List;
import java.util.Map;

public interface ItripCommentService {
    public List<ItripComment> getItripComment(Integer hotelId) throws Exception;

    public Integer getItripCommerntNum(Map<String,Object> param )throws  Exception;

}
