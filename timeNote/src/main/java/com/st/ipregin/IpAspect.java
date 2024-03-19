package com.st.ipregin;


import com.st.utils.AddressUtil;
import com.st.utils.HttpContextUtil;
import com.st.utils.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

@Aspect
@Component
@Slf4j
public class IpAspect {

    @Pointcut("@annotation(com.st.ipregin.Ip)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        String ip = IPUtil.getIpAddr(request);
        log.info(MessageFormat.format("当前IP为:[{0}]；当前IP地址解析出来的地址为:[{1}]", ip, AddressUtil.getCityInfo(ip)));
        return point.proceed();
    }

}
