//package com.xxl.job.admin.core.filter;
//
//import org.apache.commons.lang3.StringUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequestWrapper;
//
///**
// * @author weidejiang
// */
//public class ServletContextFacadeRequestWrapper extends HttpServletRequestWrapper {
//
//    public ServletContextFacadeRequestWrapper(HttpServletRequest request) {
//        super(request);
//    }
//
//
//    private String contextPath;
//
//    private String servletPath;
//
//
//    @Override
//    public String getContextPath() {
//        if(StringUtils.isNotBlank(contextPath)) {
//            return contextPath;
//        }
//        return super.getContextPath();
//    }
//
//    public void setContextPath(String contextPath) {
//
//        this.contextPath = contextPath;
//    }
//
//    @Override
//    public String getServletPath() {
//        if(StringUtils.isNotBlank(servletPath)) {
//            return servletPath;
//        }
//        return super.getServletPath();
//    }
//
//    @Override
//    public String getRequestURI() {
//        String requestURI=super.getRequestURI();
//        if(requestURI.equals(contextPath)){
//            return requestURI+"/";
//        }
//
//        return requestURI;
//    }
//
//    public void setServletPath(String servletPath) {
//        this.servletPath = servletPath;
//    }
//}
