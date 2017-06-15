package com.xiaojun.test.framework;

import com.xiaojun.test.framework.core.*;
import com.xiaojun.test.framework.util.*;
import org.apache.commons.digester3.*;

import java.io.File;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yongche on 17/6/2.
 */
public class RequestDataFactory {
    public static Logger log = LoggerFactory.getLogger(RequestDataFactory.class);

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

    public static TestCase getTestCaseFromXML(String filePath, String caseName) {
        TestSuite suite = loadTestSuiteFromXML(filePath);
        TestCase baseCase = suite.getTestCaseByName("base");
        TestCase testCase = suite.getTestCaseByName(caseName);
        if (null != testCase) {
            Map<String, TestParameter> baseInputParameterMap = baseCase.getInputParameterMap();
            Map<String, TestParameter> testInputParameterMap = testCase.getInputParameterMap();

            if (testInputParameterMap.size() > 0) {
                for (Map.Entry<String, TestParameter> entry : testInputParameterMap.entrySet()) {
                    baseInputParameterMap.put(entry.getKey(), entry.getValue());
                }
            }
        } else {
            log.error("Test case not found!");
            baseCase = null;
        }

        return baseCase;
    }

    /**
     * @param relativeNamePath relative path against XML_FILE_DIR
     */
    public static TestCase getTestCaseFromXMLByRelativePath(String relativeNamePath, String caseName) {
        String xmlFilePath = CommonConst.XML_FILE_DIR + relativeNamePath.trim();
        return getTestCaseFromXML(xmlFilePath, caseName);
    }

    public static Map<String, TestParameter> getInputParameterFromXML(String fileNamePath, String caseName) {
        return getTestCaseFromXML(fileNamePath, caseName).getInputParameterMap();
    }

    /**
     * @param relativeNamePath relative path against XML_FILE_DIR
     */
    public static Map<String, TestParameter> getInputParameterFromXMLByRelativePath(String relativeNamePath, String caseName) {
        return getTestCaseFromXMLByRelativePath(relativeNamePath, caseName).getInputParameterMap();
    }
}
