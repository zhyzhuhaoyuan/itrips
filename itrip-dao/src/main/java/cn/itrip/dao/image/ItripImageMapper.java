package cn.itrip.dao.image;

import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.vo.ItripImageVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItripImageMapper {
    public List<ItripImage> getItripImagetargetId(@Param(value = "targetId") Integer targetId) throws Exception;

    public Integer addImgUrl(ItripImage itripImage);

    public List<ItripImageVO> getItripImageListByMap(Map<String,Object> param)throws Exception;
}
