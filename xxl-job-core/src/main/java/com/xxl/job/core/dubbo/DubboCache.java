package com.xxl.job.core.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author weidejiang
 */
public class DubboCache{

    private Logger log= LoggerFactory.getLogger(DubboCache.class);




//    private Cache<String, GenericService> servicesCache= CacheBuilder.newBuilder().expireAfterAccess(
//            1L, TimeUnit.MINUTES).maximumSize(500).build();
//
//    //cacheKey方法
//    public String generateKey(ReferenceConfig<?> referenceConfig) {
//        String iName = referenceConfig.getInterface();
//        if(com.alibaba.dubbo.common.utils.StringUtils.isBlank(iName)) {
//            Class<?> clazz = referenceConfig.getInterfaceClass();
//            iName = clazz.getName();
//        }
//        if(com.alibaba.dubbo.common.utils.StringUtils.isBlank(iName)) {
//            throw new IllegalArgumentException("No interface info in ReferenceConfig" + referenceConfig);
//        }
//
//        StringBuilder ret = new StringBuilder();
//        if(! com.alibaba.dubbo.common.utils.StringUtils.isBlank(referenceConfig.getGroup())) {
//            ret.append(referenceConfig.getGroup()).append("/");
//        }
//        ret.append(iName);
//        if(! com.alibaba.dubbo.common.utils.StringUtils.isBlank(referenceConfig.getVersion())) {
//            ret.append(":").append(referenceConfig.getVersion());
//        }
//        String registry = referenceConfig.getRegistry().getAddress();
//        ret.append(":").append(registry);
//        return ret.toString();
//    }


    //获取缓存中的service
    public GenericService getServiceByCache(String group,String version,int timeout,String applicationName,String interfaceName,String url) {
        ReferenceConfig<GenericService> referenceConfig = createReferenceConfig(group,version,timeout,applicationName,interfaceName,url);
        GenericService service = null;
        ReferenceConfigCache cache=null;
        try {
            cache=ReferenceConfigCache.getCache();
            service = cache.get(referenceConfig);
//            service = servicesCache.get(serviceKey.toString(), new Callable<GenericService>() {
//                @Override
//                public GenericService call() throws Exception {
//                    GenericService genericService = referenceConfig.get();
//                    servicesCache.put(serviceKey.toString(), genericService);
//                    return genericService;
//                }
//            });
            if(service==null){
                cache.destroy(referenceConfig);
            }
        } catch (Exception e) {
            if(cache!=null){
                cache.destroy(referenceConfig);
            }
            log.error("获取dubbo服务失败，{}",e.getMessage());
        }
        return service;
    }

    /**
     *refresh
     * @param
     * @return
     */
    private ReferenceConfig createReferenceConfig(String group,String version,int timeout,String applicationName,String interfaceName,String url) {
        ApplicationConfig applicationConfig = new ApplicationConfig(applicationName);
        //多个注册中心
//        List<RegistryConfig> registries = new ArrayList<>();
        RegistryConfig registry = new RegistryConfig();
        if(!StringUtils.isEmpty(url) && url.split("://").length>1) {
            registry.setProtocol(url.split("://")[0]);
            registry.setAddress(url.split("://")[1]);
        }
        ConsumerConfig consumerConfig =  new ConsumerConfig();
        if (!StringUtils.isEmpty(group)) {
            consumerConfig.setGroup(group);
        }
        if (timeout>0) {
            consumerConfig.setTimeout(timeout*1000);
        }
        if (!StringUtils.isEmpty(version)) {
            consumerConfig.setVersion(version);
        }
        //接口引用
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setConsumer(consumerConfig);
        referenceConfig.setRegistry(registry);
        referenceConfig.setInterface(interfaceName);
        referenceConfig.setRetries(0);
        if(!StringUtils.isEmpty(group)){
            referenceConfig.setGroup(group);
        }
        if(!StringUtils.isEmpty(version)){
            referenceConfig.setVersion(version);
        }
        referenceConfig.setGeneric(true);
        referenceConfig.setCheck(false);

        return referenceConfig;
    }


}
