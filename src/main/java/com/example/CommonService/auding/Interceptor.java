package com.example.CommonService.auding;

import com.example.CommonService.entity.CommonEntity;
import com.example.CommonService.service.CommonService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

@Component
@Slf4j
public class Interceptor implements HandlerInterceptor {

    private long startTime;

    @Autowired
    private CommonService commonService;

    Date requestTime = new Date();


    @Override
    public boolean preHandle(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, Object handler) throws Exception {

        System.out.println(" pre handle");
        startTime = System.currentTimeMillis();
        Date requestTime = new Date(); // Capture the current date and time
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Request Time: " + dateFormat.format(requestTime));
        request.setAttribute("startTime", startTime);
        return true;

    }

    @Override
    public void postHandle(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        System.out.println(" post handle");


        //        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);


    }

    @Override
    public void afterCompletion(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, Object handler, Exception ex) throws Exception {

        System.out.println(" after complition");

        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        Date responseTime = new Date(); // Capture the current date and time for response
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        CommonEntity commonEntity=new CommonEntity();


        //for error trace
        String errorStackTrace = null;
        if (ex != null) {
            // Capture the exception stack trace in a variable
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            errorStackTrace = sw.toString();
            System.out.println(" error trace : " + errorStackTrace);
        }

        //for response for every request
        ContentCachingResponseWrapper wrapper;
        if (response instanceof ContentCachingResponseWrapper) {
            wrapper = (ContentCachingResponseWrapper) response;
        } else {
            wrapper = new ContentCachingResponseWrapper(response);
        }
        String responseContent = getResponse(wrapper);


        System.out.println("Response Time: " + dateFormat.format(responseTime));
        System.out.println("Response Time: " + dateFormat.format(responseTime));
        System.out.println("Status Code :" + response.getStatus());
        System.out.println("Time Taken : " + timeTaken + " ms");
        System.out.println("Context path : " + request.getRequestURI());
        System.out.println("Method Used : " + request.getMethod());
//        System.out.println("Header Name : " + request.);
        System.out.println("Content Type : " + request.getContentType());
        System.out.println("Request ID : " + request.getRequestId());
        System.out.println("Host Name : " + request.getServerName());
        System.out.println("Response Content: " + responseContent);

        // saving the fields in database
        commonEntity.setRequestTime(dateFormat.format(requestTime));
        commonEntity.setResponseTime(dateFormat.format(responseTime));
        commonEntity.setStatusCode(response.getStatus());
        commonEntity.setTimeTaken(String.valueOf(timeTaken));
        commonEntity.setRequestURI(request.getRequestURI());
        commonEntity.setRequestMethod(request.getMethod());
        commonEntity.setRequestHeaderName(getRequestHeaderNames(request));
        commonEntity.setContentType(request.getContentType());
        commonEntity.setRequestID(request.getRequestId()); //
        commonEntity.setHostName(request.getServerName());
        commonEntity.setResponse(responseContent);
        commonEntity.setErrorTrace(errorStackTrace);

        commonService.saveEntity(commonEntity);




//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }


    //for header name
    private String getRequestHeaderNames(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        StringBuilder headerNamesStr = new StringBuilder();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headerNamesStr.append(headerName).append(", ");
        }
        return headerNamesStr.toString();
    }


    //response method
    private String getResponse(ContentCachingResponseWrapper contentCachingResponseWrapper) {

        String response = IOUtils.toString(contentCachingResponseWrapper.getContentAsByteArray(), contentCachingResponseWrapper.getCharacterEncoding());
        return response;
    }
}
