package com.bs.park;

import com.bs.park.pojo.Admin;
import com.bs.park.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 林强 on 2017-04-28.
 */
public class LoginInteceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String url = httpServletRequest.getRequestURI();
        if(url.toUpperCase().contains("LOGIN")){
            return true;
        }
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        Admin admin = (Admin) httpServletRequest.getSession().getAttribute("admin");
        if(user==null&&admin==null) {
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.sendRedirect("/getPage.html");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
            Admin admin = (Admin) httpServletRequest.getSession().getAttribute("admin");
          /*  if(admin!=null)
                httpServletResponse.sendRedirect("/admin/index.jsp");
            User user = (User) httpServletRequest.getSession().getAttribute("user");
            if(user != null)
                httpServletResponse.sendRedirect("/user/goHome.do");*/
    }
}
