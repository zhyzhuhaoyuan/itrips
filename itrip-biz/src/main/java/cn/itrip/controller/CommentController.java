package cn.itrip.controller;

import cn.itrip.beans.dtos.Dto;
import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.beans.vo.comment.ItripCountCommentVo;
import cn.itrip.beans.vo.comment.ItripScoreCommentVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.ValidationToken;
import cn.itrip.service.comment.ItripCommentService;
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
    public Dto<ItripCountCommentVo> getcount(@PathVariable Integer cityId, HttpServletRequest request) {
        ItripCountCommentVo itripCountCommentVo = new ItripCountCommentVo();
        try {
            Map<String ,Object> param = new HashMap();
            param.put("hotelId", cityId);
            itripCountCommentVo.setAllcomment(itripCommentService.getItripCommerntNum(param));
            param.put("isOk",0);
            itripCountCommentVo.setImprove(itripCommentService.getItripCommerntNum(param));
            param.put("isOk",1);
            itripCountCommentVo.setIsOk(itripCommentService.getItripCommerntNum(param));
            param.remove("isOk");
            param.put("isHavingImg","1");
            itripCountCommentVo.setIsHavingImg(itripCommentService.getItripCommerntNum(param));
            return DtoUtil.returnSuccess("查询评论数量成功", itripCountCommentVo);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("查询评论数量失败", "100401");
        }
    }


}
