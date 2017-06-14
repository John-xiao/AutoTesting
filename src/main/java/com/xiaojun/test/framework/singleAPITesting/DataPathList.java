package com.xiaojun.test.framework.singleAPITesting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yongche on 17/6/14.
 */

public class DataPathList implements Iterable<DataPathItem> {
    private List<DataPathItem> list = new ArrayList<DataPathItem>();

    @Override
    public Iterator<DataPathItem> iterator() {
        return list.iterator();
    }

    public void addPathItem(DataPathItem path) {
        list.add(path);
    }
}
