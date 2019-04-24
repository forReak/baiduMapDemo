package com.design.helpPlatform.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class loginFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        //添加不过滤的内容
        String[] notFilter = new String[]{"/login","/"};


        boolean doFilter = true;
        for (String s : notFilter) {
            if (uri.equals(contextPath+s)) {
                // 如果uri中包含不过滤的uri，则不进行过滤
                doFilter = false;
                break;
            }
        }
        if (doFilter) {
            // 执行过滤
            // 从session中获取登录者实体
            Object obj = request.getSession().getAttribute("loginedUser");
            if (null == obj) {
                response.sendRedirect(contextPath);
            } else {
                // 如果session中存在登录者实体，则继续
                filterChain.doFilter(request, response);
            }
        } else {
            // 如果不执行过滤，则继续
            filterChain.doFilter(request, response);
        }
    }
}

