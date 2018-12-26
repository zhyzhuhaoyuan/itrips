package cn.itrip.service.image;

import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.dao.image.ItripImageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItripImageServiceImpl implements ItripImageService {
    @Resource
    public ItripImageMapper itripImageMapper;

    @Override
    public List<ItripImage> getItripImagetargetId(Integer targetId) throws Exception {
        return itripImageMapper.getItripImagetargetId(targetId);
    }
}
