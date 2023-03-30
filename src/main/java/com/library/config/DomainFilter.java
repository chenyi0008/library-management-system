package com.library.config;

import com.library.util.IpUitl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//滤器设置
@WebFilter("/*")
//@Configuration
public class DomainFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        //输出时间
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = new Date();
        String s = sdf.format(d);
        System.out.print(s + "  ");
        //输出url地址
        HttpServletRequest request = (HttpServletRequest) req;
        System.out.print(request.getRequestURL());
        System.out.println("   ip:" + IpUitl.getIpAddr(request));
        //修改请求头
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");//ip地址
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, token, authorization");


//        response.addHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Expose-Headers", "*");
//        response.setHeader("Access-Control-Expose-Headers", "*");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        chain.doFilter(req, res);
        System.out.println();
    }

    @Override
    public void destroy() {

    }
}
