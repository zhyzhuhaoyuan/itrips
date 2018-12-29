package cn.itrip.dao.comment;

import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.vo.comment.ItripCountCommentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItripCommentMapper {
    public List<ItripComment> getItripComment(@Param(value = "hotelId") Integer hotelId) throws Exception;

    public List<ItripCountCommentVo> getItripCommerntNum(@Param(value = "hotelId") Long hotelId)throws  Exception;

    public List<ItripComment> getItripCommentPage(Map<String,Object> param) throws Exception;

    /*public Integer getItripCountCount() ;*/

    public List<ItripImage> getItripImageImgUrl(Long targetId) throws Exception;
}
