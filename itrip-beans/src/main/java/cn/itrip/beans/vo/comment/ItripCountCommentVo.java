package cn.itrip.beans.vo.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ItripAddCommentVO",description = "添加用户评论VO")
public class ItripCountCommentVo {

    @ApiModelProperty("[必填，注：接收数字类型] 是否满意（0：有待改善 1：值得推荐）")
    private Integer improve;
    @ApiModelProperty("[必填，注：接收数字类型] 是否满意（0：有待改善 1：值得推荐）")
    private Integer isok;
    @ApiModelProperty("[必填，注：接收数字类型] 是否包含图片(当用户上传评论时检测)0:无图片 1:有图片")
    private Integer havingimg;
    @ApiModelProperty("[必填，注：接收数字类型] 是否所有的评论")
    private Integer allcomment;

    public Integer getImprove() {
        return improve;
    }

    public void setImprove(Integer improve) {
        this.improve = improve;
    }

    public Integer getIsok() {
        return isok;
    }

    public void setIsok(Integer isOk) {
        this.isok = isOk;
    }

    public Integer getHavingimg() {
        return havingimg;
    }

    public void setHavingimg(Integer isHavingImg) {
        this.havingimg = isHavingImg;
    }

    public Integer getAllcomment() {
        return allcomment;
    }

    public void setAllcomment(Integer allcomment) {
        this.allcomment = allcomment;
    }
}
