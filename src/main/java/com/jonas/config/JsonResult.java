package com.jonas.config;

import lombok.Data;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2019/04/17
 */
@Data
public class JsonResult {
    private String code;
    private String message;
    private Object data;
}
