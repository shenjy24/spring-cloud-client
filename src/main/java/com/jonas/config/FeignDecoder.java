package com.jonas.config;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.jonas.constant.BizException;
import feign.Response;
import feign.codec.Decoder;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2019/04/17
 */
@Configuration
public class FeignDecoder implements Decoder {

    @Override
    public Object decode(Response response, Type type) throws IOException {
        InputStream inputStream = response.body().asInputStream();
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while (-1 != (len = inputStream.read(buffer))) {
            result.write(buffer, 0, len);
        }
        String content = result.toString("UTF-8");

        Iterable iterable = Splitter.on(":").trimResults().omitEmptyStrings().split(content);
        List<String> item = Lists.newArrayList(iterable);
        if (3 == item.size() && "BizException".equals(item.get(0))) {
            throw new BizException(item.get(1), item.get(2));
        }

        return JSONObject.parseObject(content, type);
    }

}
