package com.xiaojun.test.framework.core;

import java.util.*;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * Created by yongche on 17/6/2.
 */
public class TestCase {
    private String name;
    private String method;
    private String service_type;
    private String service_uri;

    private Map<String, TestParameter> inputParameterMap;
    private Map<String, TestExpectResult> expectResultMap;
    private List<TestParameter> outputParameterList;

    public TestCase() {
        inputParameterMap = new HashMap<String, TestParameter>();
        expectResultMap = new HashMap<String, TestExpectResult>();
        outputParameterList = new ArrayList<TestParameter>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getService_uri() {
        return service_uri;
    }

    public void setService_uri(String service_uri) {
        this.service_uri = service_uri;
    }

    public Map<String, TestParameter> getInputParameterMap() {
        return inputParameterMap;
    }

    public Map<String, TestExpectResult> getExpectResultMap() {
        return expectResultMap;
    }

    public List<TestParameter> getOutputParameterList() {
        return outputParameterList;
    }

    public void addInputParameter(TestParameter parameter) {
        inputParameterMap.put(parameter.getName(), parameter);
    }

    public void addExpectResult(TestExpectResult result) {
        expectResultMap.put(result.getName(), result);
    }

    public void addOutputParameter(TestParameter parameter) {
        outputParameterList.add(parameter);
    }
}
