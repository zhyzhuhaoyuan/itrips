package cn.itrip.service.comment;

import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.pojo.ItripLabelDic;
import cn.itrip.beans.vo.comment.ItripCountCommentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItripCommentService {
    public List<ItripComment> getItripComment(Integer hotelId) throws Exception;

    public List<ItripCountCommentVo> getItripCommerntNum(@Param(value = "hotelId") Long hotelId)throws  Exception;

    public List<ItripComment> getItripCommentPage(Map<String,Object> param) throws Exception;

    public Integer getItripCommerntNum2(Map<String,Object> param);

    /*public Integer getItripCountCount() ;*/

    public List<ItripImage> getItripImageImgUrl(Long targetId) throws Exception;

    public List<ItripHotel> getItripCommentHotel(Long id) throws Exception;

    public List<ItripLabelDic> getItripCommenLabeOrder() throws Exception;
}
