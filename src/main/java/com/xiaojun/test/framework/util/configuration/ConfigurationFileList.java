package com.xiaojun.test.framework.util.configuration;

import org.apache.commons.digester3.Digester;

import java.io.File;
import java.util.*;

/**
 * Created by yongche on 17/6/7.
 */
public class ConfigurationFileList implements Iterable<ActiveConfiguration> {
    private static final String CONFIG_XML_FILE = "data/configuration/config.xml";
    private List<ActiveConfiguration> configList = new ArrayList<ActiveConfiguration>();

    @Override
    public Iterator<ActiveConfiguration> iterator() {
        return configList.iterator();
    }

    public void LoadFile(){
        LoadFile(CONFIG_XML_FILE);
    }

    public void LoadFile(String filePath) {
        Digester digester = new Digester();

        digester.push(this);

        digester.setValidating(false);
        digester.addObjectCreate("configurations", ConfigurationFileList.class);
        digester.addSetProperties("configurations");

        digester.addObjectCreate("configurations/configuration", ActiveConfiguration.class);
        digester.addSetProperties("configurations/configuration");
        digester.addSetNext("configurations/configuration", "addConfiguration");

        try {
            digester.parse(new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addConfiguration(ActiveConfiguration configuration) {
        configList.add(configuration);
    }
}
