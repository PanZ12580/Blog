package com.hzhang.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/16 0:50
 * @description：解决json序列化问题
 * @modified By：
 * @version: $
 */
@Configuration
public class JsonConfig implements Jackson2ObjectMapperBuilderCustomizer {

    @Override
    public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
        jacksonObjectMapperBuilder.visibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
//        jacksonObjectMapperBuilder.visibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.ANY);
//        jacksonObjectMapperBuilder.visibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.ANY);
        jacksonObjectMapperBuilder.visibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        jacksonObjectMapperBuilder.visibility(PropertyAccessor.CREATOR, JsonAutoDetect.Visibility.ANY);
    }
}
