package cn.itrip.beans.vo.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ItripAddCommentVO",description = "添加用户评论VO")
public class ItripCountCommentVo {

    @ApiModelProperty("[必填，注：接收数字类型] 是否满意（0：有待改善 1：值得推荐）")
    private Integer improve;
    @ApiModelProperty("[必填，注：接收数字类型] 是否满意（0：有待改善 1：值得推荐）")
    private Integer isOk;
    @ApiModelProperty("[必填，注：接收数字类型] 是否包含图片(当用户上传评论时检测)0:无图片 1:有图片")
    private Integer isHavingImg;
    @ApiModelProperty("[必填，注：接收数字类型] 是否所有的评论")
    private Integer allcomment;

    public Integer getImprove() {
        return improve;
    }

    public void setImprove(Integer improve) {
        this.improve = improve;
    }

    public Integer getIsOk() {
        return isOk;
    }

    public void setIsOk(Integer isOk) {
        this.isOk = isOk;
    }

    public Integer getIsHavingImg() {
        return isHavingImg;
    }

    public void setIsHavingImg(Integer isHavingImg) {
        this.isHavingImg = isHavingImg;
    }

    public Integer getAllcomment() {
        return allcomment;
    }

    public void setAllcomment(Integer allcomment) {
        this.allcomment = allcomment;
    }
}
