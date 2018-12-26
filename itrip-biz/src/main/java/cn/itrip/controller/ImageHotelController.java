package cn.itrip.controller;


import cn.itrip.beans.dtos.Dto;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.pojo.ItripLabelDic;
import cn.itrip.beans.vo.ItripImageVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.ValidationToken;
import cn.itrip.service.image.ItripImageService;
import cn.itrip.service.label.ItripLabelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@Api(value = "API", basePath = "/http://api.itrap.com/api")
@RequestMapping(value = "/api/hotelroom")
public class ImageHotelController {
    private Logger logger = Logger.getLogger(ImageHotelController.class);

    @Resource
    private ValidationToken validationToken;

    @Resource
    private ItripImageService itripImageService;

    @Resource
    private ItripLabelService itripLabelService;

    @ApiOperation(value = "查询酒店房型的图片接口", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询酒店房型的图片" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>100000 : token失效，请重登录</p>")
    @RequestMapping(value = "/getimg/{roomId}", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripImage> getimg(@PathVariable Integer roomId, HttpServletRequest request) {
        List<ItripImage> itripImages = new ArrayList<ItripImage>();
        List<ItripImageVO> itripImageVOS=new ArrayList<ItripImageVO>();
        try {
            itripImages = itripImageService.getItripImagetargetId(roomId);

            for (ItripImage itripImage : itripImages) {
                ItripImageVO itripImageVO = new ItripImageVO();
                itripImageVO.setImgUrl(itripImage.getImgUrl());
                itripImageVO.setPosition(itripImage.getPosition());
                itripImageVOS.add(itripImageVO);
            }
            System.out.println(itripImageVOS+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            return DtoUtil.returnSuccess("查询酒店房型的图片成功", itripImageVOS);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("查询酒店房型的图片失败", "100401");
        }
    }

    @ApiOperation(value = "查询酒店房型的图片接口", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询酒店房型的图片" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>100000 : token失效，请重登录</p>")
    @RequestMapping(value = "/queryhotelroombed", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripImage> queryhotelroombed( HttpServletRequest request) {
        List<ItripLabelDic> itripLabelDics = new ArrayList<ItripLabelDic>();
        try {
            itripLabelDics = itripLabelService.getItripRoomType();

            System.out.println(itripLabelDics+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            return DtoUtil.returnSuccess("查询酒店房型的图片成功", itripLabelDics);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("查询酒店房型的图片失败", "100401");
        }
    }

}
