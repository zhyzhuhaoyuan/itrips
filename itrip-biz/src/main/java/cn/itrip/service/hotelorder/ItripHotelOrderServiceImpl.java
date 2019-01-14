package cn.itrip.service.hotelorder;

import cn.itrip.beans.pojo.ItripHotelOrder;
import cn.itrip.beans.vo.order.ItripPersonalOrderRoomVO;
import cn.itrip.common.Constants;
import cn.itrip.common.EmptyUtils;
import cn.itrip.common.Page;
import cn.itrip.dao.hotelorder.ItripHotelOrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItripHotelOrderServiceImpl implements ItripHotelOrderService{
    @Resource
    public ItripHotelOrderMapper itripHotelOrderMapper;

    @Override
    public List<ItripHotelOrder> itripHotelOrderInfo(Map<String, Object> param) throws Exception {
        return itripHotelOrderMapper.itripHotelOrderInfo(param);
    }

    @Override
    public Integer itripHotelOrderStore(Map<String, Object> param) {
        return itripHotelOrderMapper.itripHotelOrderStore(param);
    }

    @Override
    public List<ItripHotelOrder> itripHotelOrderList(Map<String, Object> param) throws Exception {
        return itripHotelOrderMapper.itripHotelOrderList(param);
    }

    @Override
    public List<ItripHotelOrder> itripHotelOrderInsert(Map<String, Object> param) throws Exception {
        return itripHotelOrderMapper.itripHotelOrderInsert(param);
    }

    @Override
    public List<ItripHotelOrder> itripHotelOrderId(Integer id) throws Exception {
        return itripHotelOrderMapper.itripHotelOrderId(id);
    }

    @Override
    public ItripPersonalOrderRoomVO getItripOrdergeren(Integer id) throws Exception {
        return itripHotelOrderMapper.getItripOrdergeren(id);
    }

    @Override
    public Integer itripCount(Map<String, Object> param) throws Exception {
        return itripHotelOrderMapper.itripCount(param);
    }

    public Page<ItripHotelOrder> queryItripLabelDicPageByMap(Map<String,Object> param, Integer pageNo, Integer pageSize)throws Exception{
        Integer total = itripHotelOrderMapper.itripCount(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        System.out.println("beginPos"+param.get("beginPos"));
        System.out.println("pageSize"+param.get("pageSize"));
        List<ItripHotelOrder> itripLabelDicList = itripHotelOrderMapper.itripHotelOrderList(param);
        page.setRows(itripLabelDicList);
        return page;
    }
}
