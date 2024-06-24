package com.springframework.chapark.common;

import java.util.Enumeration;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.springframework.chapark.controller.MainController;

public class CustomMapArgumentResolver {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(CustomMapArgumentResolver.class);
    
    public Object resolveArgument(MethodParameter para, ModelAndViewContainer mvc, NativeWebRequest webReq,
            WebDataBinderFactory binderFactory) throws Exception {
         
        chaparkMap chaparkMap = new chaparkMap();
         
        HttpServletRequest req = (HttpServletRequest)webReq.getNativeRequest();
        Enumeration<?> enumeration = req.getParameterNames();
         
        String key = null;
        String[] values = null;
        while(enumeration.hasMoreElements()) {
            key = (String) enumeration.nextElement();
            values = req.getParameterValues(key);
            if(values!=null ) {
            	chaparkMap.put(key, (values.length>1)? values : values[0]);
            }
        }
         
        return chaparkMap;
    }
 
    public boolean supportsParameter(MethodParameter para) {
        return chaparkMap.class.isAssignableFrom(para.getParameterType());
    }

}
