package com.xxl.job.core.handler.impl;

import com.xxl.job.core.handler.IJobHandler;

/**
 * @author weidejiang
 */
public class HttpJobHandler extends IJobHandler {

    private String url;

    private String params;

    private String token;

    private int timeOut;

    public HttpJobHandler(String url, String params, String token, int timeOut) {
        this.url = url;
        this.params = params;
        this.token = token;
        this.timeOut = timeOut;
    }

    @Override
    public void execute() throws Exception {

    }
}
