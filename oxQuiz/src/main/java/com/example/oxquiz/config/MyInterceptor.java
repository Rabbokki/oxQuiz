package com.example.oxquiz.config;

import com.example.oxquiz.entity.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // 민쯩 까
        HttpSession session = request.getSession();
        Member currentSession = (Member) session.getAttribute("user");
        System.out.println("cs : " + currentSession);
        if (ObjectUtils.isEmpty(currentSession)) {
            response.sendRedirect("/member/login");
            return false;
        }
        return true;
    }
}
