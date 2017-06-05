package com.xiaojun.test.framework.core;

import java.util.*;

import org.apache.http.*;

/**
 * Created by yongche on 17/6/2.
 */
public class TestSuite {
    private String name;
    private String host;

    private List<TestCase> testCaseList;

    public TestSuite() {
        testCaseList = new ArrayList<TestCase>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public List<TestCase> getTestCaseList() {
        return testCaseList;
    }

    public void addTestCase(TestCase testCase) {
        this.testCaseList.add(testCase);
    }
}
