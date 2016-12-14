package com.etong.sms.controller;

import com.alibaba.fastjson.JSON;
import com.etong.sms.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by zhenghuasheng on 2016/11/30.14:33
 */
@Component
public class StringToListConverter implements Converter<String,List<User>>{
    Logger logger = LoggerFactory.getLogger(StringToListConverter.class);

    @Override
    public List<User> convert(String source) {

        if (StringUtils.isEmpty(source)) {
            return null;
        }
        List<User> list = null;
        try {
             list = JSON.parseArray(source,User.class);
        } catch (Exception e) {
            logger.error("jsonString to class failed!");
            return null;
        }
        return list;
    }
}
