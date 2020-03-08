/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: chenjianwei
 * @date: 2020-03-01
 * @version V1.0
 */
package com.chenjw.security.demo.controller;

import com.chenjw.security.demo.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**@Description: TODO(用一句话描述该文件做什么)
 * @author: chenjianwei
 * @date: 2020-03-01
 */
@ControllerAdvice   //这个注解的意思是：这个类的方法都是用来其他controller抛出的异常的
public class ControllerExceptionHandler {

    @ExceptionHandler(UserNotExistException.class)    //这个方法是处理UserNotExistException异常的
    @ResponseBody  //返回的Map需要转换成json
    @ResponseStatus(HttpStatus.BAD_REQUEST)   //定义一个返回的Http请求的状态码
    public Map<String, Object> handleUserNotException(UserNotExistException ex) {
        Map<String, Object> exceptionMessageMap = new HashMap<>();
        exceptionMessageMap.put("id", ex.getId());
        exceptionMessageMap.put("message", ex.getMessage());
        return exceptionMessageMap;
    }
}
