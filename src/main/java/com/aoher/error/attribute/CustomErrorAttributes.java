package com.aoher.error.attribute;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

    private static final String TIMESTAMP_ATTRIBUTE = "timestamp";

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);

        Object timestamp = errorAttributes.get(TIMESTAMP_ATTRIBUTE);

        if (timestamp == null) {
            errorAttributes.put(TIMESTAMP_ATTRIBUTE, DATE_FORMAT.format(new Date()));
        } else {
            errorAttributes.put(TIMESTAMP_ATTRIBUTE, DATE_FORMAT.format((Date) timestamp));
        }
        errorAttributes.put("version", "1.2");
        return errorAttributes;
    }
}
