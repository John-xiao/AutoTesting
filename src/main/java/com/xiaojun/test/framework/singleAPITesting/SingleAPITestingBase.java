package com.xiaojun.test.framework.singleAPITesting;

/**
 * Created by yongche on 17/6/15.
 */

import com.xiaojun.test.framework.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.xiaojun.test.framework.core.*;
import com.xiaojun.test.framework.RequestDataFactory;

import java.lang.reflect.Method;

abstract public class SingleAPITestingBase {
    public static Logger log = LoggerFactory.getLogger(SingleAPITestingBase.class);

    /**
     * Single interface test entrance <br>
     * Derived class must implement the abstract method and provide the data provider in annotation Test<br>
     * There are 2 data providers : <br>
     * 1) DataProvider_By_Configuration_File, collect the test xml files indicated by data_provider.xml under dir XMLCase
     * and pass them  to test method parameter one by one <br>
     * Usage :<br>
     * // @Test(dataProvider = "DataProvider_By_Configuration_File", dataProviderClass = TestDataProvider.class) <br>
     * // public void runTestCase(String xmlFleName) {...} <br>
     * 2) DataProvider_By_Parameter , collect the test xml files indicated by annotation  XmlFileParameters <br>
     * and pass them to test method parameter one by one <br>
     * Usage: <br>
     * // @Test(dataProvider = "DataProvider_By_Parameter",dataProviderClass = TestDataProvider.class) <br>
     * // @XmlFileParameters(path="XMLCase/SITDemo/Demo2", recursive = true) <br>
     * // public void runTestCase(String xmlFileName) throws SkipException {...} <br>
     *
     * @param xmlFleName test xml file passed by data provider
     */
    //@Test (dataProvider = "DataProvider_By_Configuration_File", dataProviderClass = TestDataProvider.class)
    abstract public void runTestCase(String xmlFleName) throws SkipException;

    @BeforeMethod
    public void methodSetup(Object[] parameters, Method testMethod) {
        String xmlFilePath = (String) parameters[0];
        String desc = getDescription(xmlFilePath);

        log.info("============================" + testMethod.getName() + " Starts: " + desc + "================================");
        if (descriptionIsNullOrEmpty(desc)) {
            String message = "description is a MUST for single interface testing, please check your test xml file :"
                    + desc;
            log.error(message);
        }
    }

    /**
     * 1.Log test description to identify different test End in test log <br>
     * 2.Set the test description to test result for later reporting : <br>
     * The report class uses the test method to identify different tests <br>
     * But the single interface tests invoke the same test method <br>
     * In the case, test description will be shown instead of test method in report. <br>
     * 3.Log the test result status  <br>
     */
    @AfterMethod
    public void methodCleanup(ITestResult result, Method testMethod) {
        String description = getDescription(result);
        if (descriptionIsNullOrEmpty(description)) {
            String xmlFileName = (String) result.getParameters()[0];
            description = xmlFileName;
            log.info("Test description is set to xml file name : " + description);
        }

        // Add test description to result for later reporting
        result.setAttribute("description", description);

        log.info("Test description : " + description);
        log.info("Test status : " + result.getStatus());
        log.info("============================" + testMethod.getName() + " Ends: " + description + "================================\n");
    }

    public static boolean descriptionIsNullOrEmpty(String description) {
        return (null == description || description.isEmpty());
    }

    /**
     * Check if the description is null or empty, if true, then throw SkipExcetption in order to skip the test
     *
     * @param description Attribute description in test xml file to describe the test case
     * @param xmlFileName test xml file name, will be shown when test description is null or empty
     */
    public static void skipTestIfNullOrEmptyDescription(String description, String xmlFileName) {
        if (descriptionIsNullOrEmpty(description)) {
            // Skip the test if no description is set
            // Because all the tests invoke the same test method
            // No description, in test report, all results begin with same test method name thus no test is identified
            throw new SkipException(
                    "description is a MUST for single interface testing, please check your test xml file :" +
                            xmlFileName
            );
        }
    }

    public static String getDescription(String xmlFileName) {
        TestSuite suite = RequestDataFactory.loadTestSuiteFromXML(xmlFileName);
        TestCase testCase = suite.getTestCaseList().get(0);
        return testCase.getDescription();
    }

    public static String getDescription(ITestResult result) {
        String xmlFileName = result.getParameters()[0].toString();
        return getDescription(xmlFileName);
    }
}
