package cn.solr.controller;

import cn.itrip.beans.dtos.Dto;
import cn.itrip.beans.pojo.ItripAreaDic;
import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.beans.pojo.ItripLabelDic;
import cn.itrip.beans.vo.ItripAreaDicVO;
import cn.itrip.beans.vo.ItripLabelDicVO;
import cn.itrip.beans.vo.comment.ItripListCommentVO;
import cn.itrip.beans.vo.hotel.SearchHotCityVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.EmptyUtils;
import cn.itrip.common.Page;
import cn.itrip.service.areadic.ItripAreaDicService;
import cn.itrip.service.labeldic.ItripLabelDicService;
import cn.solr.service.SearchHotelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@RequestMapping(value = "/api/hotellist")//还不用
public class HotelListController {

    private Logger logger = Logger.getLogger(HotelListController.class);

    @Resource
    private SearchHotelService searchHotelService;


    @ApiOperation(value = "根据热门城市查询6个酒店", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "根据热门城市查询6个酒店" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>10201 : hotelId不能为空 </p>" +
            "<p>10202 : 系统异常,获取失败</p>")
    @RequestMapping(value = "/searchItripHotelListByHotCity", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<Page<ItripListCommentVO>> searchItripHotelListByHotCity(@RequestBody SearchHotCityVO vo) {
        if (EmptyUtils.isNotEmpty(vo)) {
            DtoUtil.returnFail("type不能为空", "10201");
        }
        Map<String ,Object> param=new HashMap<>();
        param.put("cityId",vo.getCityId());
        List list=null;
        return list;
    }

}
