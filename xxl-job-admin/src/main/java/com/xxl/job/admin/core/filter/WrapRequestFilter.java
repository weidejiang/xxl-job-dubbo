//package com.xxl.job.admin.core.filter;
//
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @author weidejiang
// */
//public class WrapRequestFilter  extends OncePerRequestFilter {
//
//    private static final String[] Paths=new String[]{"/xxl-job-admin",""};
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        ServletContextFacadeRequestWrapper wrapper=new ServletContextFacadeRequestWrapper(httpServletRequest);
//        String path=getMatchingContextPathForRequest(httpServletRequest);
//        if(path!=null){
//            wrapper.setContextPath(httpServletRequest.getContextPath()+path);
//            String newPath=httpServletRequest.getServletPath().substring(path.length());
//            if(newPath.length()==0){
//                newPath="/";
//            }
//            wrapper.setServletPath(newPath);
//        }
//        filterChain.doFilter(wrapper,httpServletResponse);
//
//    }
//
//
//    public String getMatchingContextPathForRequest(HttpServletRequest httpServletRequest){
//        for(String path:Paths){
//            if(httpServletRequest.getServletPath().startsWith(path)){
//                return path;
//            }
//
//        }
//return null;
//
//    }
//}
