package com.xxl.job.core.handler.impl;

import com.alibaba.dubbo.rpc.service.GenericService;
import com.fosun.health.job.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.dubbo.DubboCache;
import com.xxl.job.core.handler.IJobHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.HashMap;

/**
 * @author weidejiang
 */
public class DubboJobHandler extends IJobHandler {

    private String group;

    private int timeout;

    private String applicationName;

    private String className;

    private String url;

    private String version;

    private String method;

    public DubboJobHandler(String group, int timeout, String applicationName, String className, String url,String version,String method) {
        this.group = group;
        this.timeout = timeout;
        this.applicationName = applicationName;
        this.className = className;
        this.url = url;
        this.version=version;
        this.method=method;
    }

    private DubboJobHandler() {
    }

    private static final Logger logger= LoggerFactory.getLogger(DubboJobHandler.class);


    @Override
    public void execute() throws Exception {
        DubboCache dubboCache=new DubboCache();
        logger.info("请求dubbo的url={},className={}",url,className);
        GenericService genericService = dubboCache.getServiceByCache(group, version, timeout, applicationName, className, url);
        if(StringUtils.isEmpty(method)){
            // 默认方法
            method="excute";
        }
        if(genericService!=null){
            HashMap hashMap=(HashMap) genericService.$invoke(method,null,null);
            if(hashMap!=null) {
                if(200==(int) hashMap.get("code")){
                    XxlJobHelper.handleSuccess();
                    return;
                }else {
                    XxlJobHelper.handleFail(hashMap.get("msg")+"");
                }
            }
        }
        XxlJobHelper.handleFail("dubbo链接失败");
    }
}
