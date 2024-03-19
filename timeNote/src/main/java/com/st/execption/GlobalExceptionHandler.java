package com.st.execption;

import com.st.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @ClassName: GlobalExceptionHandler
 * @Version: 1.0
 * @Description: 全局异常处理
 * @Author song
 * @Date 2022/8/4 20:58
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class, Service.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 异常处理方法
     *
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        log.error(ex.getMessage());
        return R.fail("当前学号已经登记过了!","Java服务器");
    }

    @ExceptionHandler
    public R<String> exceptionHandler(ServiceException e){
        log.error("运行时异常: {}",e.getMessage());
        return R.fail(e.getMessage(),"Java服务器");
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public R<String> exceptionHandler(MissingRequestHeaderException e){
        log.error(e.getMessage());
        return R.fail("请先登录!","Java服务器");
    }
}
