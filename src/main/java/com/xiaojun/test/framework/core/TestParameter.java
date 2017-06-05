package com.xiaojun.test.framework.core;

/**
 * Created by yongche on 17/6/2.
 */
public class TestParameter {
    private String name;
    private String value;
    private String alias;

    public TestParameter() {
    }

    public TestParameter(String name, String value) {
        this(name, value, "");
    }

    public TestParameter(String name, String value, String alias) {
        this.name = name;
        this.value = value;
        this.alias = alias;
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        return name + " : " + value;
    }

}
