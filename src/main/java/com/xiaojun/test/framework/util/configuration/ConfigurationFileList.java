package com.xiaojun.test.framework.util.configuration;

import org.apache.commons.digester3.Digester;

import java.io.File;
import java.util.*;

/**
 * Created by yongche on 17/6/7.
 */
public class ConfigurationFileList implements Iterable<ActiveConfiguration> {
    public static final String CONFIG_XML_FILE = "data/configuration/config.xml";
    private List<ActiveConfiguration> configList = new ArrayList<ActiveConfiguration>();

    @Override
    public Iterator<ActiveConfiguration> iterator() {
        return configList.iterator();
    }

    public static ConfigurationFileList getConfigurationList() {
        return getConfigurationList(CONFIG_XML_FILE);
    }

    public static ConfigurationFileList getConfigurationList(String filePath) {
        Digester digester = new Digester();

        digester.setValidating(false);
        digester.addObjectCreate("configurations", ConfigurationFileList.class);
        digester.addSetProperties("configurations");

        digester.addObjectCreate("configurations/configuration", ActiveConfiguration.class);
        digester.addSetProperties("configurations/configuration");
        digester.addSetNext("configurations/configuration", "addConfiguration");

        ConfigurationFileList configList = null;
        try {
            configList = digester.parse(new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return configList;
    }

    public void addConfiguration(ActiveConfiguration configuration) {
        configList.add(configuration);
    }
}
