package com.xiaojun.test.testcase;

/**
 * Created by yongche on 17/6/15.
 */

import com.xiaojun.test.framework.singleAPITesting.*;
import org.testng.SkipException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.testng.annotations.*;
import org.testng.Assert;

public class SingleAPITestCaseDemo1 extends SingleAPITestingBase {
    public static Logger log = LoggerFactory.getLogger(SingleAPITestCaseDemo1.class);

    @Override
    @Test(dataProvider = "Configuration_File", dataProviderClass = SingleAPITestingDataProvider.class)
    public void runTestCase(String xmlFleName) throws SkipException {
        skipTestIfNullOrEmptyDescription(getDescription(xmlFleName), xmlFleName);
        Assert.assertTrue(true);
    }
}
