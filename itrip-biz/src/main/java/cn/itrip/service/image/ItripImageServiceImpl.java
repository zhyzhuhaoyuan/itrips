package cn.itrip.service.image;

import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.vo.ItripImageVO;
import cn.itrip.dao.image.ItripImageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ItripImageServiceImpl implements ItripImageService {
    @Resource
    public ItripImageMapper itripImageMapper;

    @Override
    public List<ItripImage> getItripImagetargetId(Integer targetId) throws Exception {
        return itripImageMapper.getItripImagetargetId(targetId);
    }

    @Override
    public Integer addImgUrl(ItripImage itripImage) {
        return itripImageMapper.addImgUrl(itripImage);
    }

    @Override
    public List<ItripImageVO> getItripImageListByMap(Map<String, Object> param) throws Exception {
        return itripImageMapper.getItripImageListByMap(param);
    }
}
