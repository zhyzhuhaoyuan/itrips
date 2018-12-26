package cn.solr.controller;


import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.vo.hotel.SearchHotCityVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.EmptyUtils;
import cn.solr.entity.ItripHotelVO;
import cn.solr.service.SearchHotelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Api(value = "API", basePath = "/http://api.itrap.com/api")
@RequestMapping(value = "/api/hotellist")
public class HotelListController {

    private Logger logger = Logger.getLogger(HotelListController.class);

    @Resource
    private SearchHotelService searchHotelService;


    @ApiOperation(value = "根据热门城市查询6个酒店", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "根据热门城市查询6个酒店" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>10201 : hotelId不能为空 </p>" +
            "<p>10202 : 系统异常,获取失败</p>")
    @RequestMapping(value = "/searchItripHotelListByHotCity", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public Dto<ItripHotelVO> searchItripHotelListByHotCity(@RequestBody SearchHotCityVO vo) {
        if (EmptyUtils.isNotEmpty(vo)) {
            DtoUtil.returnFail("type不能为空", "10201");
        }
        Map<String ,Object> param=new HashMap<>();
        param.put("cityId",vo.getCityId());
        List<ItripHotelVO> list=searchHotelService.searchItripHotelByCity(vo.getCityId().toString(),6);
        return DtoUtil.returnDataSuccess(list);
    }

}
