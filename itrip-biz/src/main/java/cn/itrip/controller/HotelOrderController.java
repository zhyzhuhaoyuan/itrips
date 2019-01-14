package cn.itrip.controller;


import cn.itrip.beans.dtos.Dto;
import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.beans.pojo.ItripHotelOrder;
import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.beans.vo.order.*;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.Page;
import cn.itrip.common.ValidationToken;
import cn.itrip.service.hotelorder.ItripHotelOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Api(value = "API", basePath = "/http://api.itrap.com/api")
@RequestMapping(value = "/api/hotelorder")
public class HotelOrderController {
    private Logger logger = Logger.getLogger(CommentController.class);

    @Resource
    private ItripHotelOrderService itripHotelOrderService;

    @Resource
    private ValidationToken validationToken;

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

    @ApiOperation(value = "查询个人酒店订单列表", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询个人酒店订单列表" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>100000 : token失效，请重登录</p>")
    @RequestMapping(value = "/getpersonalorderlist", method = RequestMethod.POST)
    @ResponseBody
    public Dto<ItripListHotelOrderVO> getpersonalorderlist(@RequestBody ItripSearchOrderVO vo,HttpServletRequest request) {

        List<ItripListHotelOrderVO> itripListCommentVOS=new ArrayList<>();
        ItripListHotelOrderVO itripSearchOrderVO=new ItripListHotelOrderVO();
        String tokenString = request.getHeader("token");
        ItripUser currentUser = validationToken.getCurrentUser(tokenString);
        try {
            Map<String,Object> param=new HashMap<>();
            param.put("orderStatus",vo.getOrderStatus());
            param.put("orderType", vo.getOrderType());
            param.put("userId",currentUser.getId());
            param.put("linkUserName",vo.getLinkUserName());
            param.put("creationDate",vo.getEndDate());
            param.put("orderNo",vo.getOrderNo());
            param.put("modifyDate",vo.getStartDate());

            System.out.println("linkUserName"+vo.getLinkUserName()+"creationDate"+vo.getEndDate()+"orderNo"+vo.getOrderNo()+"modifyDate"+vo.getStartDate());
            Page<ItripHotelOrder> page1=itripHotelOrderService.queryItripLabelDicPageByMap(param,vo.getPageSize(),vo.getPageNo());

            Page page=new Page();
            page.setBeginPos(page1.getBeginPos());
            page.setCurPage(page1.getCurPage());
            page.setPageCount(page1.getPageCount());
            page.setPageSize(page1.getPageSize());
            page.setTotal(page1.getTotal());

            System.out.println("beginpos"+page1.getBeginPos()+"curpage"+page1.getCurPage()+"total"+page1.getTotal()+"pageCount"+page1.getPageCount()+"sizepage"+page1.getPageSize());


            for (ItripHotelOrder itripHotelOrder : page1.getRows()) {
                BeanUtils.copyProperties(itripHotelOrder,itripSearchOrderVO);
                itripListCommentVOS.add(itripSearchOrderVO);
            }
            page.setRows(itripListCommentVOS);
            return DtoUtil.returnSuccess("查询个人酒店订单列表成功", page);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("查询个人酒店订单列表失败", "100401");
        }
    }

    @ApiOperation(value = "查询评论内容列表", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询评论内容列表" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>100000 : token失效，请重登录</p>")
    @RequestMapping(value = "/addhotelorder", method = RequestMethod.POST)
    @ResponseBody
    public Dto<ItripHotelOrder> addhotelorder(@RequestBody ItripAddHotelOrderVO vo) {

        List<ItripAddHotelOrderVO> itripAddHotelOrderVO=new ArrayList<>();
        ItripAddHotelOrderVO itripAddHotelOrderVO1=new ItripAddHotelOrderVO();
        try {
            Map param = new HashMap();
            param.put("id",vo.getId());
            List<ItripHotelOrder> itripHotelName = itripHotelOrderService.itripHotelOrderInfo(param);
            param.put("checkInDate", vo.getCheckInDate());
            param.put("checkOutDate", vo.getCheckOutDate());
            param.put("count", vo.getCount());
            param.put("hotelId", vo.getHotelId());
            param.put("roomId", vo.getRoomId());
            param.put("id",vo.getId());
            param.put("orderType",vo.getOrderType());
            param.put("hotelName",vo.getHotelName());
            param.put("noticePhone",vo.getNoticePhone());
            param.put("noticeEmail",vo.getNoticeEmail());
            param.put("specialRequirement",vo.getSpecialRequirement());
            param.put("isNeedInvoice",vo.getIsNeedInvoice());
            param.put("invoiceType",vo.getInvoiceType());
            param.put("invoiceHead",vo.getInvoiceHead());
            param.put("linkUser",itripHotelName);

            List<ItripHotelOrder> itripHotelOrders = itripHotelOrderService.itripHotelOrderInsert(param);
            for (ItripHotelOrder itripHotelOrder : itripHotelOrders) {
                BeanUtils.copyProperties(itripHotelOrder,itripAddHotelOrderVO1);
                itripAddHotelOrderVO.add(itripAddHotelOrderVO1);
            }
            return DtoUtil.returnSuccess("查询评论内容列表成功", itripAddHotelOrderVO);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("查询评论内容列表失败", "100401");
        }
    }

    @ApiOperation(value = "查询评论内容列表", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询评论内容列表" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>100000 : token失效，请重登录</p>")
    @RequestMapping(value = "/getpersonalorderinfo/{orderId}", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripPersonalHotelOrderVO> getpersonalorderinfo(@PathVariable Integer orderId) {

        List<ItripHotelOrder> itripHotelOrders=new ArrayList<>();
        ItripPersonalHotelOrderVO itripPersonalHotelOrderVO=new ItripPersonalHotelOrderVO();
        try {
            itripHotelOrders=itripHotelOrderService.itripHotelOrderId(orderId);
            for (ItripHotelOrder itripHotelOrder : itripHotelOrders) {
                itripPersonalHotelOrderVO.setId(itripHotelOrder.getId());
                itripPersonalHotelOrderVO.setBookType(itripHotelOrder.getBookType());
                itripPersonalHotelOrderVO.setCreationDate(itripHotelOrder.getCreationDate());
                itripPersonalHotelOrderVO.setNoticePhone(itripHotelOrder.getNoticePhone());
                itripPersonalHotelOrderVO.setOrderNo(itripHotelOrder.getOrderNo());
                itripPersonalHotelOrderVO.setOrderStatus(itripHotelOrder.getOrderStatus());
                itripPersonalHotelOrderVO.setPayAmount(itripHotelOrder.getPayAmount());
                itripPersonalHotelOrderVO.setPayType(itripHotelOrder.getPayType());
                itripPersonalHotelOrderVO.setRoomPayType(1);
            }
            if (itripPersonalHotelOrderVO.getOrderStatus()==0){
                itripPersonalHotelOrderVO.setProcessNode("2");
                itripPersonalHotelOrderVO.setOrderProcess("已提交-->待支付");
            }else if(itripPersonalHotelOrderVO.getOrderStatus()==1){
                itripPersonalHotelOrderVO.setProcessNode("3");
                itripPersonalHotelOrderVO.setOrderProcess("已提交-->待支付-->已取消");
            }else if(itripPersonalHotelOrderVO.getOrderStatus()==2){
                itripPersonalHotelOrderVO.setProcessNode("3");
                itripPersonalHotelOrderVO.setOrderProcess("已提交-->待支付-->支付成功");
            }else{
                itripPersonalHotelOrderVO.setProcessNode("4");
                itripPersonalHotelOrderVO.setOrderProcess("已提交-->待支付-->支付成功-->已入住-->已点评");
            }

            return DtoUtil.returnSuccess("查询评论内容列表成功", itripPersonalHotelOrderVO);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("查询评论内容列表失败", "100401");
        }
    }


    @RequestMapping(value = "/getpersonalorderroominfo/{orderId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripPersonalOrderRoomVO> getPersonalOrderRoominfo(@PathVariable Integer orderId) {

        ItripPersonalOrderRoomVO itripPersonalOrderRoomVO = new ItripPersonalOrderRoomVO();
        try {
            itripPersonalOrderRoomVO = itripHotelOrderService.getItripOrdergeren(orderId);
            itripPersonalOrderRoomVO.setCheckInWeek(2);
            itripPersonalOrderRoomVO.setCheckOutWeek(3);
        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("获取酒店评分成功",itripPersonalOrderRoomVO);
    }
}
