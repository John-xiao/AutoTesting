package com.xiaojun.test.framework;

import com.xiaojun.test.framework.core.*;
import org.apache.commons.digester3.*;

import java.io.File;
import java.util.*;

/**
 * Created by yongche on 17/6/2.
 */
public class RequestDataFactory {
    private static String XML_FILE_DIR = "data/request_data/";

    public static TestSuite loadTestSuiteFromXML(String filePath) {
        Digester digester = new Digester();

        digester.setValidating(false);

        digester.addObjectCreate("testsuite", TestSuite.class);
        digester.addSetProperties("testsuite");

        digester.addObjectCreate("testsuite/testcase", TestCase.class);
        digester.addSetProperties("testsuite/testcase");

        digester.addObjectCreate("testsuite/testcase/input/parameter", TestParameter.class);
        digester.addSetProperties("testsuite/testcase/input/parameter");
        digester.addBeanPropertySetter("testsuite/testcase/input/parameter", "value");
        digester.addSetNext("testsuite/testcase/input/parameter", "addInputParameter");

        digester.addObjectCreate("testsuite/testcase/expectresult/result", TestExpectResult.class);
        digester.addSetProperties("testsuite/testcase/expectresult/result");
        digester.addBeanPropertySetter("testsuite/testcase/expectresult/result", "value");
        digester.addSetNext("testsuite/testcase/expectresult/result", "addExpectResult");

        digester.addObjectCreate("testsuite/testcase/output/parameter", TestParameter.class);
        digester.addSetProperties("testsuite/testcase/output/parameter");
        digester.addBeanPropertySetter("testsuite/testcase/output/parameter", "value");
        digester.addSetNext("testsuite/testcase/output/parameter", "addOutputParameter");

        digester.addSetNext("testsuite/testcase", "addTestCase");

        TestSuite ts = null;

        try {
            ts = digester.parse(new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ts;
    }

    public static Map<String, TestParameter> getInputParameterFromXML(String fileName, String caseName) {
        String xmlFilePath = XML_FILE_DIR + fileName.trim();
        TestSuite suite = loadTestSuiteFromXML(xmlFilePath);
        TestCase testCase = suite.getTestCaseByName(caseName);
        return testCase.getInputParameterMap();
    }
}
