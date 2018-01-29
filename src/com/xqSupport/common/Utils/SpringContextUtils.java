package com.xqSupport.common.Utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by mac on 2018/1/27.
 */
public class SpringContextUtils implements ApplicationContextAware {
    private static ApplicationContext context;

    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
        SpringContextUtils.context = context;
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
