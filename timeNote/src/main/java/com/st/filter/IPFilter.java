package com.st.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author:song
 * @Package: com.st.filter
 * @Project: timeNote
 * @name: IPFilter
 * @Filename: IPFilter
 * @Date: 2023-02-21 16:34
 */
@Component
public class IPFilter implements Filter{
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
