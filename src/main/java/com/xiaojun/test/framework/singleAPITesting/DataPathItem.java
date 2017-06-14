package com.xiaojun.test.framework.singleAPITesting;

/**
 * Created by yongche on 17/6/14.
 */
public class DataPathItem {
    private String name;
    private String value;
    private boolean enable;
    private boolean recursive;

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

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isRecursive() {
        return recursive;
    }

    public void setRecursive(boolean recursive) {
        this.recursive = recursive;
    }
}
