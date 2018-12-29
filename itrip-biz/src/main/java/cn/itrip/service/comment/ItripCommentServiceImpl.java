package cn.itrip.service.comment;

import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.vo.comment.ItripCountCommentVo;
import cn.itrip.dao.comment.ItripCommentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ItripCommentServiceImpl implements ItripCommentService {

    @Resource
    public ItripCommentMapper itripCommentMapper;

    @Override
    public List<ItripComment> getItripComment(Integer hotelId) throws Exception {
        return itripCommentMapper.getItripComment(hotelId);
    }

    @Override
    public List<ItripCountCommentVo> getItripCommerntNum(Long hotelId) throws Exception {
        return itripCommentMapper.getItripCommerntNum(hotelId);
    }

    @Override
    public List<ItripComment> getItripCommentPage(Map<String, Object> param) throws Exception {
        return itripCommentMapper.getItripCommentPage(param);
    }

    @Override
    public List<ItripImage> getItripImageImgUrl(Long targetId) throws Exception {
        return itripCommentMapper.getItripImageImgUrl(targetId);
    }

    /*@Override
    public Integer getItripCountCount()  {
        return itripCommentMapper.getItripCountCount();
    }*/
}
