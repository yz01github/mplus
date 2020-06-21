package com.youngzeu.mplus.config.cached;

import com.youngzeu.mplus.entity.base.BaseEntity;
import com.youngzeu.mplus.entity.user.UserDO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description: []
 * title: SessionCached
 *
 * @author <a href="mailto:yangzhi@asiainfo.com">yangzhi</a>
 * created 2020/6/15
 */
public class SessionCached {
    // ConcurrentHashMap 使用分段锁机制保证线程安全，比HashTable好，HashMap线程不安全
    private static ThreadLocal<Map<String,Object>> local =
            ThreadLocal.withInitial(ConcurrentHashMap :: new);

    public static Map<String, Object> get(){
        return local.get();
    }

    public static void put(Map<String, Object> map){
        local.get().putAll(map);
    }

    public static <K extends String, V> void put(K k, V v){
        local.get().put(k, v);
    }

    /**
     * 设置用户信息
     * @param user
     */
    public static <T extends BaseEntity> void setUser(T user) {
        put("USER_INFO", user);
    }

    public static UserDO getUser() {
        return (UserDO) get().get("USER_INFO");
    }

    public static void loadPerission(UserDO userDO) {
        String userId = userDO.getUserId();
        if(StringUtils.isBlank(userId)){
            return;
        }
        // todo get redis data
        // if redisData == null -> redis load
        // load end -> add to ThreadLocal

    }
}
