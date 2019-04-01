package com.jonas.config;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.jonas.constant.BizException;
import com.jonas.constant.JsonResult;
import com.jonas.constant.SystemCode;
import com.jonas.util.logging.SystemLogger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2018/11/01
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public JsonResult handleException(Exception ex) {
        if (ex instanceof BizException) {
            Iterable iterable = Splitter.on(":").trimResults().omitEmptyStrings().split(ex.getMessage());
            List<String> item = Lists.newArrayList(iterable);
            return new JsonResult(item.get(1), item.get(2), null);
        }

        SystemLogger.error(ex, "handle exception");
        return new JsonResult(SystemCode.SERVER_ERROR);
    }
}
