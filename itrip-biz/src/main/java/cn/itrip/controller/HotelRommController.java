package cn.itrip.controller;

import cn.itrip.beans.dtos.Dto;
import cn.itrip.beans.pojo.ItripAreaDic;
import cn.itrip.beans.pojo.ItripHotelRoom;
import cn.itrip.beans.pojo.ItripLabelDic;
import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.beans.vo.ItripAreaDicVO;
import cn.itrip.beans.vo.ItripLabelDicVO;
import cn.itrip.beans.vo.hotelroom.ItripHotelRoomVO;
import cn.itrip.beans.vo.hotelroom.SearchHotelRoomVO;
import cn.itrip.beans.vo.order.ItripListHotelOrderVO;
import cn.itrip.common.DateUtil;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.EmptyUtils;
import cn.itrip.common.ValidationToken;
import cn.itrip.service.area.ItripHotService;
import cn.itrip.service.areadic.ItripAreaDicService;
import cn.itrip.service.hotelroom.ItripHotelRoomService;
import cn.itrip.service.labeldic.ItripLabelDicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 酒店信息Controller
 * <p/>
 * 包括API接口：
 * 1、根据酒店id查询酒店拓展属性
 * 2、根据酒店id查询酒店介绍，酒店政策，酒店设施
 * 3、根据酒店id查询酒店特色属性列表
 * 4、根据type 和target id 查询酒店图片
 * 5、查询热门城市
 * 6、查询热门商圈列表（搜索-酒店列表）
 * 7、查询数据字典特色父级节点列表（搜索-酒店列表）
 * 8、根据酒店id查询酒店特色、商圈、酒店名称（视频文字描述）
 * <p/>
 * 注：错误码（100201 ——100300）
 * <p/>
 * Created by hanlu on 2017/5/9.
 */

@Controller
@Api(value = "API", basePath = "/http://api.itrap.com/api")
@RequestMapping(value = "/api/hotelroom")
public class HotelRommController {

    private Logger logger = Logger.getLogger(HotelController.class);

    @Resource
    private ItripHotelRoomService itripHotelRoomService;

    @Resource
    private ValidationToken validationToken;


    @ApiOperation(value = "查询酒店房型列表", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json"+
            "<p>10201 : hotelId不能为空 </p>" +
            "<p>10202 : 系统异常,获取失败</p>")
    @RequestMapping(value = "/queryhotelroombyhotel", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public  Dto<List<ItripHotelRoomVO>> queryhotelroombyhotel(@RequestBody SearchHotelRoomVO vo) {

        List<List<ItripHotelRoomVO>> listListRommvo=new ArrayList<>();
        try {
            Map<String, Object> param = new HashMap();
            if (EmptyUtils.isEmpty(vo.getHotelId())) {
                return DtoUtil.returnFail("酒店ID不能为空", "100303");
            }
            if (EmptyUtils.isEmpty(vo.getStartDate()) || EmptyUtils.isEmpty(vo.getEndDate())) {
                return DtoUtil.returnFail("必须填写酒店入住及退房时间", "100303");
            }
            if (EmptyUtils.isNotEmpty(vo.getStartDate()) && EmptyUtils.isNotEmpty(vo.getEndDate())) {
                if (vo.getStartDate().getTime() > vo.getEndDate().getTime()) {
                    return DtoUtil.returnFail("入住时间不能大于退房时间", "100303");
                }
                List<Date> dates = DateUtil.getBetweenDates(vo.getStartDate(), vo.getEndDate());
                param.put("timesList", dates);
            }

            vo.setIsHavingBreakfast(EmptyUtils.isEmpty(vo.getIsHavingBreakfast()) ? null : vo.getIsHavingBreakfast());
            vo.setIsBook(EmptyUtils.isEmpty(vo.getIsBook()) ? null : vo.getIsBook());
            vo.setIsTimelyResponse(EmptyUtils.isEmpty(vo.getIsTimelyResponse()) ? null : vo.getIsTimelyResponse());
            vo.setRoomBedTypeId(EmptyUtils.isEmpty(vo.getRoomBedTypeId()) ? null : vo.getRoomBedTypeId());
            vo.setIsCancel(EmptyUtils.isEmpty(vo.getIsCancel()) ? null : vo.getIsCancel());
            vo.setPayType(EmptyUtils.isEmpty(vo.getPayType()) ? null : vo.getPayType());

            param.put("hotelId", vo.getHotelId());
            param.put("isBook", vo.getIsBook());
            param.put("isHavingBreakfast", vo.getIsHavingBreakfast());
            param.put("isTimelyResponse", vo.getIsTimelyResponse());
            param.put("roomBedTypeId", vo.getRoomBedTypeId());
            param.put("isCancel", vo.getIsCancel());
            param.put("payType", vo.getPayType()==3?null:vo.getPayType());
            if (EmptyUtils.isNotEmpty(vo)) {
                List<ItripHotelRoomVO> itripHotelRommid=itripHotelRoomService.getItripHotelRommid(param);
                for (ItripHotelRoomVO itripHotelRoomVO : itripHotelRommid) {
                    List<ItripHotelRoomVO> itripHotelRoomVOS=new ArrayList<>();
                    itripHotelRoomVOS.add(itripHotelRoomVO);
                    listListRommvo.add(itripHotelRoomVOS);
                }
            } else {
                DtoUtil.returnFail("type不能为空", "10201");
            }
        } catch (Exception e) {
            DtoUtil.returnFail("查询酒店房型列表失败", "10202");
            e.printStackTrace();
        }
        return DtoUtil.returnDataSuccess(listListRommvo);
    }

}
