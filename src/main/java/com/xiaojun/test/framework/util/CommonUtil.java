package com.xiaojun.test.framework.util;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yongche on 17/6/14.
 */
public class CommonUtil {
    public static Logger log = LoggerFactory.getLogger(CommonUtil.class);

    /**
     * Get all files under some a directory
     *
     * @param path
     * @param recursive
     * @return path list of all files
     * @Param filenameFilter filter by file name , indicates which type of files will be searched for
     */
    public static List<String> getAllFiles(String path, boolean recursive, FileFilter fileFilter) {

        //log.info("Try getting all files under "+ path +" , recursive is " + recursive);
        List<String> fileList = new ArrayList<>();

        File root = new File(path);
        if (!root.exists()) {
            log.info(path + " : does NOT exist!!!");

            return fileList;
        }

        /**
         * 如果没有传递文件过滤器，就接受所有文件
         */
        if (null == fileFilter) {
            fileFilter = (file) -> file.isFile();
        }

        if (root.isDirectory()) {
            File[] files = root.listFiles(fileFilter);
            for (File file : files) {
                log.info("Found : " + file.getPath());
                fileList.add(file.getPath());
            }

            if (recursive) {
                File[] dirs = root.listFiles(file -> file.isDirectory());
                for (File dir : dirs) {
                    log.info("Found : " + dir.getPath() + ", type is directory, recursive is " + recursive);
                    fileList.addAll(getAllFiles(dir.getPath(), recursive, fileFilter));
                }
            }
        } else {
            if (fileFilter.accept(root)) {
                log.info(path + " is some a file path actually");
                log.info("Found : " + root.getPath());
                fileList.add(root.getPath());
            } else {
                log.info(path + " is some a file path actually");
                log.info(path + " is NOT the target file type : " + fileFilter.toString());
            }
        }

        return fileList;
    }
}
