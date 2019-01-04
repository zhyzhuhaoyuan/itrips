package cn.itrip.controller;


import cn.itrip.beans.dtos.Dto;
import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.pojo.ItripLabelDic;
import cn.itrip.beans.vo.ItripImageVO;
import cn.itrip.beans.vo.comment.*;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.Page;
import cn.itrip.common.ValidationToken;
import cn.itrip.service.comment.ItripCommentService;
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
@RequestMapping(value = "/api/comment")
public class CommentController {
    private Logger logger = Logger.getLogger(CommentController.class);

    @Resource
    private ValidationToken validationToken;

    @Resource
    private ItripCommentService itripCommentService;

    @ApiOperation(value = "查询酒店评分", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询酒店评分" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>100000 : token失效，请重登录</p>")
    @RequestMapping(value = "/gethotelscore/{cityId}", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripScoreCommentVO> gethotelscore(@PathVariable Integer cityId, HttpServletRequest request) {
        List<ItripComment> itripComments = new ArrayList<ItripComment>();
        List<ItripScoreCommentVO> itripScoreCommentVOS=new ArrayList<ItripScoreCommentVO>();
        ItripScoreCommentVO itripScoreCommentVO = new ItripScoreCommentVO();
        try {
            itripComments = itripCommentService.getItripComment(cityId);

            for (ItripComment itripComment : itripComments) {

                itripScoreCommentVO.setAvgFacilitiesScore(itripComment.getFacilitiesScore());
                itripScoreCommentVO.setAvgHygieneScore(itripComment.getHygieneScore());
                itripScoreCommentVO.setAvgPositionScore(itripComment.getPositionScore());
                itripScoreCommentVO.setAvgScore(itripComment.getScore());
                itripScoreCommentVO.setAvgServiceScore(itripComment.getServiceScore());
                itripScoreCommentVOS.add(itripScoreCommentVO);
            }
            System.out.println(itripScoreCommentVOS+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            return DtoUtil.returnSuccess("查询酒店评分成功", itripScoreCommentVO);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("查询酒店评分失败", "100401");
        }
    }

    @ApiOperation(value = "查询评论数量", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询评论数量" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>100000 : token失效，请重登录</p>")
    @RequestMapping(value = "/getcount/{cityId}", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripCountCommentVo> getCount(@PathVariable Long cityId) {

        ItripCountCommentVo itripCountCommentVo=null;
        List<ItripCountCommentVo> itripCountCommentVos=null;
        try {
            itripCountCommentVos=itripCommentService.getItripCommerntNum(cityId);
            for (ItripCountCommentVo countCommentVo : itripCountCommentVos) {
                itripCountCommentVos=new ArrayList();
                itripCountCommentVo= new ItripCountCommentVo();
                itripCountCommentVo.setIsok(countCommentVo.getIsok());
                itripCountCommentVo.setHavingimg(countCommentVo.getHavingimg());
                itripCountCommentVo.setAllcomment(countCommentVo.getAllcomment());
                itripCountCommentVo.setImprove(countCommentVo.getImprove());
                itripCountCommentVos.add(itripCountCommentVo);
            }
            return DtoUtil.returnSuccess("查询评论数量成功", itripCountCommentVo);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("查询评论数量失败", "100401");
        }
    }


    @ApiOperation(value = "查询评论内容列表", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询评论内容列表" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>100000 : token失效，请重登录</p>")
    @RequestMapping(value = "/getcommentlist", method = RequestMethod.POST)
    @ResponseBody
    public Dto<ItripListCommentVO> getcommentlist(@RequestBody ItripSearchCommentVO vo) {

        List<ItripListCommentVO> itripListCommentVOS=new ArrayList<>();
        List<ItripComment> itripComments=new ArrayList<>();
        ItripListCommentVO itripListCommentVO=new ItripListCommentVO();
        List<ItripCountCommentVo> itripCountCommentVos=new ArrayList();

        Page page=new Page();
        try {
            Map<String,Object> param=new HashMap<>();
            param.put("hotelId",vo.getHotelId());
            System.out.println("hotelId>>>>>>>"+vo.getHotelId());
            if (vo.getIsHavingImg().equals(-1)){
                param.put("isHavingImg",null);
            }
            if (vo.getIsHavingImg().equals(1)){
                param.put("isHavingImg",1);
            }
            if (vo.getIsHavingImg().equals(0)){
                param.put("isHavingImg",0);
            }

            if (vo.getIsOk().equals(1)){
                param.put("isOk",1);
            }
            if (vo.getIsOk().equals(-1)){
                param.put("isOk",null);
            }
            if (vo.getIsOk().equals(0)){
                param.put("isOk",0);
            }
            System.out.println("isOk>>>>>>>"+vo.getIsOk());
            param.put("pageSize",vo.getPageSize());
            param.put("pageNo",vo.getPageNo());
            System.out.println("pageNo>>>>>>>"+vo.getPageNo());
            itripComments=itripCommentService.getItripCommentPage(param);

            /*Integer curPage=itripCommentService.getItripCountCount();*/

            Integer totel=itripCommentService.getItripCommerntNum2(param);
            page.setTotal(totel);
            System.out.println("当前总次数》》》》》》》》》》》》》》》》"+totel);

            page.setBeginPos(0);
            page.setCurPage(vo.getPageNo());
            page.setPageCount(vo.getPageSize());
            page.setPageSize(vo.getPageSize());

            System.out.println(itripComments+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>itripComments");

            for (ItripComment itripComment : itripComments) {
                BeanUtils.copyProperties(itripComment,itripListCommentVO);
                itripListCommentVOS.add(itripListCommentVO);
            }
            page.setRows(itripListCommentVOS);

            return DtoUtil.returnSuccess("查询评论内容列表成功", page);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("查询评论内容列表失败", "100401");
        }
    }

    @ApiOperation(value = "查询评论附带的图片", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询评论附带的图片" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>100000 : token失效，请重登录</p>")
    @RequestMapping(value = "/getimg/{commentId}", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripImageVO> getimg(@PathVariable Long commentId, HttpServletRequest request) {
        List<ItripImage> itripImages = new ArrayList<ItripImage>();
        List<ItripImageVO> itripImageVOS=new ArrayList<>();
        ItripImageVO itripImageVO=new ItripImageVO();

        try {
            itripImages = itripCommentService.getItripImageImgUrl(commentId);
            for (ItripImage itripImage : itripImages) {
                itripImageVO.setPosition(itripImage.getPosition());
                itripImageVO.setImgUrl(itripImage.getImgUrl());
                itripImageVOS.add(itripImageVO);
            }
;
            return DtoUtil.returnSuccess("查询评论附带的图片成功", itripImageVOS);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("查询评论附带的图片失败", "100401");
        }
    }

    @ApiOperation(value = "查询酒店详情（评论）", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询酒店详情（评论）" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>100000 : token失效，请重登录</p>")
    @RequestMapping(value = "/gethoteldesc/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripHotelDescVO> gethoteldesc(@PathVariable Long id) {

        ItripHotelDescVO itripCountCommentVo=new ItripHotelDescVO();
        List<ItripHotelDescVO> itripHotelDescVOS=new ArrayList<>();
        List<ItripHotel> itripHotels=new ArrayList<>();
        try {
            itripHotels=itripCommentService.getItripCommentHotel(id);
            for (ItripHotel itripComment : itripHotels) {
                itripCountCommentVo.setHotelId(itripComment.getId());
                itripCountCommentVo.setHotelLevel(itripComment.getHotelLevel());
                itripCountCommentVo.setHotelName(itripComment.getHotelName());
                itripHotelDescVOS.add(itripCountCommentVo);
            }

            return DtoUtil.returnSuccess("查询酒店详情（评论）成功", itripCountCommentVo);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("查询酒店详情（评论）失败", "100401");
        }
    }

    @ApiOperation(value = "查询旅游类型列表", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询旅游类型列表" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>100000 : token失效，请重登录</p>")
    @RequestMapping(value = "/gettraveltype", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripHotelDescVO> gettraveltype() {
        List<ItripLabelDic> itripHotels=new ArrayList<>();
        try {
            itripHotels=itripCommentService.getItripCommenLabeOrder();
            return DtoUtil.returnSuccess("查询旅游类型列表成功", itripHotels);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("查询旅游类型列表失败", "100401");
        }
    }

}
