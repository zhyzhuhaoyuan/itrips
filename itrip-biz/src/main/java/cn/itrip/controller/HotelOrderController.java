package cn.itrip.controller;

import cn.itrip.beans.dtos.Dto;
import cn.itrip.beans.pojo.ItripHotelOrder;
import cn.itrip.beans.vo.comment.ItripListCommentVO;
import cn.itrip.beans.vo.order.PreAddOrderVO;
import cn.itrip.beans.vo.order.ValidateRoomStoreVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.EmptyUtils;
import cn.itrip.service.hotelorder.ItripHotelOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@Api(value = "API", basePath = "/http://api.itrap.com/api")
@RequestMapping(value = "/api/hotelorder")
public class HotelOrderController {
    private Logger logger = Logger.getLogger(CommentController.class);

    @Resource
    private ItripHotelOrderService itripHotelOrderService;

    @ApiOperation(value = "查询评论内容列表", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询评论内容列表" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>100000 : token失效，请重登录</p>")
    @RequestMapping(value = "/getpreorderinfo", method = RequestMethod.POST)
    @ResponseBody
    public Dto<ItripHotelOrder> getpreorderinfo(@RequestBody PreAddOrderVO vo) {

        List<PreAddOrderVO> itripScoreCommentVOS = new ArrayList<>();
        PreAddOrderVO preAddOrderVO=new PreAddOrderVO();
            try {
                Map param = new HashMap();
                param.put("checkInDate", vo.getCheckInDate());
                param.put("checkOutDate", vo.getCheckOutDate());
                param.put("count", vo.getCount());
                param.put("hotelId", vo.getHotelId());
                param.put("roomId", vo.getRoomId());
                List<ItripHotelOrder> itripHotelOrders = itripHotelOrderService.itripHotelOrderInfo(param);
                for (ItripHotelOrder itripHotelOrder : itripHotelOrders) {
                    BeanUtils.copyProperties(itripHotelOrder,preAddOrderVO);
                    itripScoreCommentVOS.add(preAddOrderVO);
                }
                Map param1 = new HashMap();
                param1.put("hotelId", vo.getHotelId());
                param1.put("roomId", vo.getRoomId());
                Integer score = itripHotelOrderService.itripHotelOrderStore(param1);
                for (PreAddOrderVO itripScoreCommentVO : itripScoreCommentVOS) {
                    itripScoreCommentVO.setStore(score);
                }
            return DtoUtil.returnSuccess("查询评论内容列表成功", itripScoreCommentVOS);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("查询评论内容列表失败", "100401");
        }
    }
}
