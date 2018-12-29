package cn.itrip.dao.user;

import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.beans.vo.comment.ItripCountCommentVo;
import cn.itrip.dao.comment.ItripCommentMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestItripUserMapper {


    @Test
    public void testGetItripUserById() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        ItripUserMapper itripUserMapper = ctx.getBean(ItripUserMapper.class);
        try {
            ItripUser itripUser = itripUserMapper.getItripUserById((long) 12);
            System.out.println(itripUser.getId() + " " + itripUser.getUserCode() + " " + itripUser.getUserPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetCount() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        ItripCommentMapper itripCommentMapper=ctx.getBean(ItripCommentMapper.class);
        try {
            List<ItripCountCommentVo> itripUser = itripCommentMapper.getItripCommerntNum(Long.highestOneBit(1));
            for (ItripCountCommentVo itripCountCommentVo : itripUser) {
                System.out.println(itripCountCommentVo.getAllcomment() + ">>>>231111111" );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
