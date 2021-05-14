package com.qicheng.ssm.intercepter;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
public class LoginInterceptor implements HandlerInterceptor {
 
    @Autowired
    HttpSession session ;
 
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // 执行完毕，返回前拦截
    }
 
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        // 在处理过程中，执行拦截
    }
 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        // 在拦截点执行前拦截，如果返回true则不执行拦截点后的操作（拦截成功）
        // 返回false则不执行拦截
//        String currentUserName = (String) session.getAttribute("username");
        String currentUserName =(String)request.getSession().getAttribute("username");
        //String uri = request.getRequestURI(); // 获取登录的uri，这个是不进行拦截的
        //if(session.getAttribute("LOGIN_USER")!=null || uri.indexOf("system/login")!=-1) {// 说明登录成功 或者 执行登录功能
        if(currentUserName!=null) {
            // 登录成功不拦截
            return true;
        }else {
            // 拦截后进入登录页面
            //执行这里表示用户身份需要验证，跳转到登录界面
        	response.sendRedirect(request.getContextPath()+"/login.jsp");
        	System.out.println(request.getContextPath()+"/login.jsp");
//            request.getRequestDispatcher(request.getContextPath()+"/user/login.jsp").forward(request, response);
            return false;
        }
    }
}
