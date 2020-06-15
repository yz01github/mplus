package com.youngzeu.mplus.interceptor;

import com.youngzeu.mplus.config.cached.SessionCached;
import com.youngzeu.mplus.entity.user.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Session;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * description: [登录拦截]
 * title: CsrfInterceptor
 *
 * @author <a href="mailto:yangzhi@asiainfo.com">yangzhi</a>
 * created 2020/1/19
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    private static final ThreadLocal<HttpSession> threadSession = new ThreadLocal<>();

    public static Session getSession(){
        Session s = (Session) threadSession.get();
        return s;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.debug("LoginInterceptor.preHandle begin...");
        String requestURI = request.getRequestURI();
        List<String> noCheckURI = Arrays.asList("");
        if(noCheckURI.contains(requestURI)){
            return true;// 登录或者其他不需要登录就可以访问的主页
        }
        HttpSession session = request.getSession();
        Object httpToken = request.getAttribute("token");
        Object sessionToken = session.getAttribute("token");
        if(!Objects.equals(httpToken, sessionToken)){
            // token不同，跳转到登录页面,
            // 登录接口生成token、查询用户基本信息，存入session
            log.debug("token 校验失败，跳转登录...");
            session.setAttribute("TODO_REQUEST", request);
        }else{
            // 登录后返回原先需要访问的页面（如果有） begin
            HttpServletRequest todoRequest = (HttpServletRequest)session.getAttribute("TODO_REQUEST");
            if(Objects.nonNull(todoRequest)){
                request = todoRequest;
                session.removeAttribute("TODO_REQUEST");
            }
            // 登录后返回原先需要访问的页面 end
            // 存储线程变量 begin; 权限等数据应在首次登录时，缓存的redis，后续每次更新权限的同时更新redis
            UserDO userInfo = (UserDO)session.getAttribute("USER_INFO");
            SessionCached.setUser(userInfo);
            // 存储线程变量 end

        }


        // check User
        String sessionId = session.getId();
        log.debug("LoginInterceptor.preHandle end...");
        return true;// 只有返回true才会继续向下执行，返回false取消当前请求
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println(">>>MyInterceptor2>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println(">>>MyInterceptor2>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }
}
