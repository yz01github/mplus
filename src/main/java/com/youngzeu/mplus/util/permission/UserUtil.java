package com.youngzeu.mplus.util.permission;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youngzeu.mplus.dao.UserDao;
import com.youngzeu.mplus.entity.user.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * description: []
 * title: UserUtil
 *
 * @author <a href="mailto:yangzhi@asiainfo.com">yangzhi</a>
 * created 2020/6/15
 */
@Component
public class UserUtil {

    private static UserDao userDao;

    // 静态工具类依赖于其他实例，不能直接在static变量上@Autowired,这样无法注入，使用时会报空指针
    // 解决方法1：构造器注入所需实例
    /*@Autowired
    private UserUtil(UserDao userDao){
        UserUtil.userDao = userDao;
    }*/

    // 解决方法2：set方法注入
    /*@Autowired
    public void setComponent(UserDao userDao){
        UserUtil.userDao = userDao;
    }*/

    // 解决方法3：@PostConstruct 和 @Autowired结合，
    // @PostConstruct作用于servlet生命周期的注解，用它注解的方法在构造函数之后就会被调用。
    // Autowired注册一个实例属性，在构造器之后，执行本方法，把该实例属性赋值给静态属性
    @Autowired
    private UserDao instanceDao;
    @PostConstruct
    private void beforeInit() {
        // 构造器之后，使用之前调用
        UserUtil.userDao = this.instanceDao;
    }

    /**
     * description: [根据userId、userAccount查询User信息]
     * @param   UserEntity          查询条件
     * @return  List<UserEntity>    查询结果
     * @author <a href="mailto:yangzhi@asiainfo.com">yangzhi</a>
     * created 2020/6/15
     */
    public static List<UserEntity> qryUserInfo(UserEntity UserEntity){
        boolean notBlankUserId = StringUtils.isNotBlank(UserEntity.getUserId());
        boolean notBlankAccount = StringUtils.isNotBlank(UserEntity.getUserAccount());
        if(!notBlankUserId && !notBlankAccount){
            // TODO 报错，不能全为空
        }
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(notBlankUserId, "userId", UserEntity.getUserId());
        wrapper.eq(notBlankAccount, "userAccount", UserEntity.getUserAccount());
        return userDao.selectList(wrapper);
    }

    public static boolean userIsExist(UserEntity UserEntity){
        return !CollectionUtils.isEmpty(qryUserInfo(UserEntity));
    }
}
