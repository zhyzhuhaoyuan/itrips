package cn.itrip.controller;

import cn.itrip.beans.dtos.Dto;
import cn.itrip.beans.pojo.*;
import cn.itrip.beans.vo.ItripAreaDicVO;
import cn.itrip.beans.vo.ItripLabelDicVO;
import cn.itrip.beans.vo.hotel.HotelVideoDescVO;
import cn.itrip.beans.vo.hotel.ItripSearchDetailsHotelVO;
import cn.itrip.beans.vo.hotel.ItripSearchFacilitiesHotelVO;
import cn.itrip.beans.vo.hotel.ItripSearchPolicyHotelVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.ValidationToken;
import cn.itrip.service.area.ItripHotService;
import cn.itrip.service.label.ItripLabelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@Api(value = "API", basePath = "/http://api.itrap.com/api")
@RequestMapping(value = "/api/hotel")
public class UserhotelController {
    private Logger logger = Logger.getLogger(UserhotelController.class);


    @Resource
    private ValidationToken validationToken;

    @Resource
    private ItripHotService itripHotService;

    @Resource
    private ItripLabelService itripLabelService;


    @ApiOperation(value = "查询热门城市接口", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询热门城市信息" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>100000 : token失效，请重登录</p>")
    @RequestMapping(value = "/queryhotcity/{cityId}", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripAreaDicVO> queryhotcity(@PathVariable Integer cityId, HttpServletRequest request) {
        List<ItripAreaDic> userLinkUserList = new ArrayList<ItripAreaDic>();
        List<ItripAreaDicVO> areaDicVOS=new ArrayList<ItripAreaDicVO>();
            try {
                userLinkUserList = itripHotService.getItripHotById(cityId);

                for (ItripAreaDic areaDic : userLinkUserList) {
                    ItripAreaDicVO itripAreaDicVO = new ItripAreaDicVO();
                    itripAreaDicVO.setId(areaDic.getId());
                    itripAreaDicVO.setName(areaDic.getName());
                    areaDicVOS.add(itripAreaDicVO);
                }
                System.out.println(areaDicVOS+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                return DtoUtil.returnSuccess("获取热门城市信息成功", areaDicVOS);
            } catch (Exception e) {
                e.printStackTrace();
                return DtoUtil.returnFail("获取热门城市信息失败", "100401");
            }
    }

    @ApiOperation(value = "查询所有酒店特色", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询所有酒店特色" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>100000 : token失效，请重登录</p>")
    @RequestMapping(value = "/queryhotelfeature", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripLabelDicVO> queryhotelfeature(HttpServletRequest request) {
        List<ItripLabelDic> userLinkUserList = new ArrayList<ItripLabelDic>();
        List<ItripLabelDicVO> labelDicVOS=new ArrayList<ItripLabelDicVO>();
            try {
                userLinkUserList = itripLabelService.getItripLabel();
                for (ItripLabelDic itripLabelDic : userLinkUserList) {
                    ItripLabelDicVO itripLabelDicVO=new ItripLabelDicVO();
                    itripLabelDicVO.setDescription(itripLabelDic.getDescription());
                    itripLabelDicVO.setId(itripLabelDic.getId());
                    itripLabelDicVO.setName(itripLabelDic.getName());
                    itripLabelDicVO.setPic(itripLabelDic.getPic());
                    labelDicVOS.add(itripLabelDicVO);
                }

                System.out.println(labelDicVOS+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                return DtoUtil.returnSuccess("获取热门城市信息成功", labelDicVOS);
            } catch (Exception e) {
                e.printStackTrace();
                return DtoUtil.returnFail("获取热门城市信息失败", "100401");
            }
    }

    @ApiOperation(value = "查询城市商圈", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询城市商圈" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>100000 : token失效，请重登录</p>")
    @RequestMapping(value = "/querytradearea/{cityId}", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripAreaDicVO> querytradearea(@PathVariable Long cityId,HttpServletRequest request) {
        List<ItripAreaDic> userLinkUserList = new ArrayList<ItripAreaDic>();
        List<ItripAreaDicVO> areaDicVOS=new ArrayList<ItripAreaDicVO>();
            try {
                userLinkUserList = itripHotService.getItripHotByIdsq(cityId);

                for (ItripAreaDic areaDic : userLinkUserList) {
                    ItripAreaDicVO itripAreaDicVO = new ItripAreaDicVO();
                    itripAreaDicVO.setId(areaDic.getId());
                    itripAreaDicVO.setName(areaDic.getName());
                    areaDicVOS.add(itripAreaDicVO);
                }
                System.out.println(areaDicVOS+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                return DtoUtil.returnSuccess("查询城市商圈信息成功", areaDicVOS);
            } catch (Exception e) {
                e.printStackTrace();
                return DtoUtil.returnFail("查询城市商圈信息失败", "100401");
            }
    }

    @ApiOperation(value = "查询视频信息", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询视频信息" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>100000 : token失效，请重登录</p>")
    @RequestMapping(value = "/getvideodesc/{cityId}", method = RequestMethod.GET)
    @ResponseBody
    public Dto<HotelVideoDescVO> getvideodesc(@PathVariable Integer cityId,HttpServletRequest request) {
        List<String> userLinkUserList = null;
        ItripHotel itripHotels=null;
        List<String> itripLabelDics=null;

        List<HotelVideoDescVO> hotelVideoDescVOS=new ArrayList<HotelVideoDescVO>();
            try {
                itripHotels = itripHotService.getItripHotelIdsp(cityId);
                itripLabelDics=itripLabelService.getItripLabelName(cityId);
                userLinkUserList=itripHotService.getItripHotByName(cityId);


                    HotelVideoDescVO hotelVideoDescVO = new HotelVideoDescVO();
                    hotelVideoDescVO.setHotelName(itripHotels.getHotelName());
                    hotelVideoDescVO.setHotelFeatureList(itripLabelDics);
                    hotelVideoDescVO.setTradingAreaNameList(userLinkUserList);
                    hotelVideoDescVOS.add(hotelVideoDescVO);



                return DtoUtil.returnSuccess("获取视频信息成功2",hotelVideoDescVO);
            } catch (Exception e) {
                e.printStackTrace();
                return DtoUtil.returnFail("获取视频信息失败1", "100401");
            }
    }

    @ApiOperation(value = "查询酒店描述和特色 ", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询酒店描述和特色 " +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>100000 : token失效，请重登录</p>")
    @RequestMapping(value = "/queryhoteldetails/{cityId}", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripSearchDetailsHotelVO> queryhoteldetails(@PathVariable Integer cityId,HttpServletRequest request) {
        ItripHotel itripHotels=null;


        try {
            itripHotels = itripHotService.getItripHotelIdsp(cityId);
            List<ItripSearchDetailsHotelVO> itripSearchDetailsHotelVOS=itripLabelService.getItripLabelHotel(cityId);

                ItripSearchDetailsHotelVO itripSearchDetailsHotelVO = new ItripSearchDetailsHotelVO();

                itripSearchDetailsHotelVO.setName("酒店介绍");
                itripSearchDetailsHotelVO.setDescription(itripHotels.getDetails());
                itripSearchDetailsHotelVOS.add(itripSearchDetailsHotelVO);

            return DtoUtil.returnSuccess("查询酒店描述和特色 ",itripSearchDetailsHotelVOS);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("查询酒店描述和特色 ", "100401");
        }
    }

    @ApiOperation(value = "查询酒店设施", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询酒店设施" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>100000 : token失效，请重登录</p>")
    @RequestMapping(value = "/queryhotelfacilities/{cityId}", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripSearchFacilitiesHotelVO> queryhotelfacilities(@PathVariable Integer cityId, HttpServletRequest request) {
        ItripHotel itripHotelArrayList =null;
        List<ItripSearchFacilitiesHotelVO> itripSearchFacilitiesHotelVOS=new ArrayList<ItripSearchFacilitiesHotelVO>();
        try {
            itripHotelArrayList = itripHotService.getItripHotelfacilities(cityId);
            System.out.println(itripHotelArrayList+">>>123456789");

                ItripSearchFacilitiesHotelVO itripSearchFacilitiesHotelVO = new ItripSearchFacilitiesHotelVO();
                itripSearchFacilitiesHotelVO.setFacilities(itripHotelArrayList.getFacilities());
                itripSearchFacilitiesHotelVOS.add(itripSearchFacilitiesHotelVO);

            System.out.println(itripSearchFacilitiesHotelVOS+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            return DtoUtil.returnSuccess("查询酒店设施成功", itripSearchFacilitiesHotelVO.getFacilities());
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("查询酒店设施失败", "100401");
        }
    }

    @ApiOperation(value = "查询酒店设施", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询酒店设施" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>100000 : token失效，请重登录</p>")
    @RequestMapping(value = "/queryhotelpolicy/{cityId}", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripSearchPolicyHotelVO> queryhotelpolicy(@PathVariable Integer cityId, HttpServletRequest request) {
        ItripHotel itripHotelArrayList = null;
        List<ItripSearchPolicyHotelVO> itripSearchPolicyHotelVOS=new ArrayList<ItripSearchPolicyHotelVO>();
        try {
            itripHotelArrayList = itripHotService.getItripHotelPolicy(cityId);
            System.out.println(itripHotelArrayList+">>>123456789");
                ItripSearchPolicyHotelVO itripSearchPolicyHotelVO = new ItripSearchPolicyHotelVO();
                itripSearchPolicyHotelVO.setHotelPolicy(itripHotelArrayList.getHotelPolicy());
                itripSearchPolicyHotelVOS.add(itripSearchPolicyHotelVO);
            System.out.println(itripSearchPolicyHotelVOS+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            return DtoUtil.returnSuccess("查询酒店设施成功", itripSearchPolicyHotelVO.getHotelPolicy());
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("查询酒店设施失败", "100401");
        }
    }

}
