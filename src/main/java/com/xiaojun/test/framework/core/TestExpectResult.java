package com.xiaojun.test.framework.core;

/**
 * Created by yongche on 17/6/2.
 */
public class TestExpectResult {
    private String name;
    private String value;
    private boolean check = true;

    public TestExpectResult() {
    }

    public TestExpectResult(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(String check) {
        if (check.toLowerCase().equals("false")) {
            this.check = false;
        }
    }

    @Override
    public String toString() {
        return name + ":" + value;
    }
}
