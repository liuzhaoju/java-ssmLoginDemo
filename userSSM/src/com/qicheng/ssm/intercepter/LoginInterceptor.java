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
        // ִ����ϣ�����ǰ����
    }
 
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        // �ڴ�������У�ִ������
    }
 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        // �����ص�ִ��ǰ���أ��������true��ִ�����ص��Ĳ��������سɹ���
        // ����false��ִ������
//        String currentUserName = (String) session.getAttribute("username");
        String currentUserName =(String)request.getSession().getAttribute("username");
        //String uri = request.getRequestURI(); // ��ȡ��¼��uri������ǲ��������ص�
        //if(session.getAttribute("LOGIN_USER")!=null || uri.indexOf("system/login")!=-1) {// ˵����¼�ɹ� ���� ִ�е�¼����
        if(currentUserName!=null) {
            // ��¼�ɹ�������
            return true;
        }else {
            // ���غ�����¼ҳ��
            //ִ�������ʾ�û������Ҫ��֤����ת����¼����
        	response.sendRedirect(request.getContextPath()+"/login.jsp");
        	System.out.println(request.getContextPath()+"/login.jsp");
//            request.getRequestDispatcher(request.getContextPath()+"/user/login.jsp").forward(request, response);
            return false;
        }
    }
}
