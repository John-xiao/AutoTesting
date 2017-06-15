package com.xiaojun.test.testcase;

/**
 * Created by yongche on 17/6/6.
 */

import com.xiaojun.test.framework.core.*;
import com.xiaojun.test.framework.*;
import com.xiaojun.test.framework.util.configuration.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.testng.annotations.*;
import org.testng.Assert;
import org.testng.ITestResult;
import com.xiaojun.test.framework.singleAPITesting.*;

import java.util.*;

public class TestCaseDemo {
    public static Logger log = LoggerFactory.getLogger(TestCaseDemo.class);

    @Test
    public void load_data_sample() {
//        Map<String, TestParameter> inputParam = RequestDataFactory.getInputParameterFromXML("request_data_sample.xml", "test_case_sample");
//        Assert.assertEquals(inputParam.get("user_id").getValue(), "26016450_0");

        String host = ConfigurationUtil.getValue("HOST", "CHARGE");
        Assert.assertEquals(host, "charge.stage.yongche.org");
    }


    @Test(dataProvider = "Configuration_File",dataProviderClass = SingleAPITestingDataProvider.class)
    public void single_API_testing_sample1(String requestDataFile){
        Assert.assertTrue(true);
    }

    @Test(dataProvider = "Path_Parameter",dataProviderClass = SingleAPITestingDataProvider.class)
    @RequestDataPath(path = "data/request_data/", recursive = true)
    public void single_API_testing_sample2(String requestDataFile){
        Assert.assertTrue(true);
    }
}
