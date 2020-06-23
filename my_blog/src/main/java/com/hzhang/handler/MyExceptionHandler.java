package com.hzhang.handler;

import com.hzhang.exception.NotFoundException;
import com.hzhang.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/5/19 10:49
 * @description： 全局异常处理器
 * @modified By：
 * @version: $
 */
@RestControllerAdvice
public class MyExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        logger.error("Request URL: {}, Response Status_Code: {}, Exception: {}", request.getRequestURL(), response.getStatus(), e);
//        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
//            throw e;
//        }
        if(e instanceof NoHandlerFoundException || e instanceof NotFoundException){
            return Result.builder()
                    .flag(false)
                    .errorMsg(e.getMessage())
                    .statusCode(404)
                    .build();
        }else{
            return Result.builder()
                    .flag(false)
                    .errorMsg(e.getMessage())
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();
        }
    }
}
