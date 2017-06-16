package com.xiaojun.test.framework.util;

import org.testng.ITestResult;

/**
 * Created by yongche on 17/6/16.
 */
public class TestNGResultStatus {

    public static String toString(int status) {
        String statusString;

        switch (status) {
            case ITestResult.SUCCESS:
                statusString = "SUCCESS";
                break;
            case ITestResult.FAILURE:
                statusString = "FAILURE";
                break;
            case ITestResult.SKIP:
                statusString = "SKIP";
                break;
            case ITestResult.SUCCESS_PERCENTAGE_FAILURE:
                statusString = "SUCCESS_PERCENTAGE_FAILURE";
                break;
            case ITestResult.STARTED:
                statusString = "STARTED";
                break;
            default:
                statusString = "Unknown result status : " + String.valueOf(status);

        }

        return statusString;
    }

    public enum EnumStatus {
        SUCCESS(ITestResult.SUCCESS),
        FAILURE(ITestResult.FAILURE),
        SKIP(ITestResult.SKIP),
        SUCCESS_PERCENTAGE_FAILURE(ITestResult.SUCCESS_PERCENTAGE_FAILURE),
        STARTED(ITestResult.STARTED);

        private int status;

        EnumStatus(int status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return TestNGResultStatus.toString(this.status);
        }
    }
}