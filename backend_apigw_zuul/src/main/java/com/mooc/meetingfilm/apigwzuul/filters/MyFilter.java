package com.mooc.meetingfilm.apigwzuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @date: 2021/10/7 7:50
 * @ClassName: MyFilter
 * @description:
 */
@Slf4j
public class MyFilter extends ZuulFilter {

    /**
     * Filter类型
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filter执行顺序， 0排在最前
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否有拦截
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * Filter的具体业务逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {

        // 类型ThreadLocal
        RequestContext requestContext = RequestContext.getCurrentContext();

        HttpServletRequest request = requestContext.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            log.info("headName:{}, headValue:{}", headerName, request.getHeader(headerName));
        }


        return null;
    }
}
