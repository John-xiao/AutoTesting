package com.xiaojun.test.framework.singleAPITesting;

/**
 * Created by yongche on 17/6/14.
 */

import org.apache.commons.digester3.Digester;
import org.testng.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.xiaojun.test.framework.util.*;

public class SingleAPITestingDataProvider {
    public static Logger log = LoggerFactory.getLogger(SingleAPITestingDataProvider.class);
    public static final String CONFIGURATION_FILE = "data/single_testing/single_testing_config.xml";

    @DataProvider(name = "Configuration_File")
    public static Iterator<Object[]> dataFromConfiguration() {
        List<Object[]> paramList = new ArrayList<Object[]>();

        DataPathList pathList = getDataPathList(CONFIGURATION_FILE);

        pathList.forEach((dataPathItem) -> {
            if (dataPathItem.isEnable()) {
                paramList.addAll(getFilePathList(dataPathItem.getValue(), dataPathItem.isRecursive()));
            }
        });

        log.info("File Path List:");
        paramList.forEach((fileList) -> {
            log.info("" + fileList[0]);
        });

        return paramList.iterator();
    }

    public static DataPathList getDataPathList(String path) {
        Digester digester = new Digester();

        digester.setValidating(false);
        digester.addObjectCreate("dataprovider", DataPathList.class);
        digester.addSetProperties("dataprovider");

        digester.addObjectCreate("dataprovider/location", DataPathItem.class);
        digester.addSetProperties("dataprovider/location");
        digester.addSetNext("dataprovider/location", "addPathItem");

        DataPathList collection = null;
        try {
            collection = digester.parse(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return collection;
    }

    @DataProvider(name = "Path_Parameter")
    public static Iterator<Object[]> dataFromPath(final Method testMethod) throws RuntimeException {
        RequestDataPath dataPath = testMethod.getAnnotation(RequestDataPath.class);

        if (null != dataPath) {
            List<Object[]> filePathList = getFilePathList(dataPath.path(), dataPath.recursive());
            log.info("File Path List:");
            filePathList.forEach((fileList) -> {
                log.info("" + fileList[0]);
            });
            return filePathList.iterator();
        } else {
            throw new RuntimeException("Could not find the annotation: RequestDataPath in method: " + testMethod.getName());
        }
    }

    private static List<Object[]> getFilePathList(String path, boolean recursive) {
        List<Object[]> filePathList = new ArrayList<Object[]>();

        CommonUtil.getAllFiles(path, recursive, null).forEach((filePath) -> {
            filePathList.add(new Object[]{filePath});
        });

        return filePathList;
    }
}
