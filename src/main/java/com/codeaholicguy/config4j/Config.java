package com.codeaholicguy.config4j;

import com.codeaholicguy.config4j.util.KeyUtil;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * @author hoangnn
 */
public class Config {

    private static final Logger LOGGER = Logger.getLogger(Config.class);

    private static Map<String, Config> instances;

    private final Map<String, String> configData = new HashMap();
    private CompositeConfiguration config;

    private String configFolderPath;
    private String configFilename;

    private static final String DEFAULT_CONFIG_FILENAME = "config.ini";
    private static final String EMPTY = "";

    public static Config getInstance(String configFolderPath, String configFilename) {
        String key = KeyUtil.generateKey(configFolderPath, configFilename);
        Config instance;
        if (Objects.isNull(instances)) {
            instances = new HashMap<>();
            instance = new Config(configFolderPath, configFilename);
            instances.put(key, instance);
        } else {
            instance = instances.get(key);
            if (Objects.isNull(instance)) {
                instance = new Config(configFolderPath, configFilename);
                instances.put(key, instance);
            }
        }

        return instance;
    }

    public static Config getInstance() {
        String key = KeyUtil.generateKey(EMPTY, DEFAULT_CONFIG_FILENAME);
        Config instance;
        if (Objects.isNull(instances)) {
            instances = new HashMap<>();
            instance = new Config(null, null);
            instances.put(key, instance);
        } else {
            instance = instances.get(key);
            if (Objects.isNull(instance)) {
                instance = new Config(null, null);
                instances.put(key, instance);
            }
        }

        return instance;
    }

    private Config(String configFolderPath, String configFilename) {
        this.configFolderPath = Objects.isNull(configFolderPath) ? EMPTY : configFolderPath;
        this.configFilename = Objects.isNull(configFilename) ? DEFAULT_CONFIG_FILENAME : configFilename;

        initConfigData();
    }

    private void initConfigData() {
        config = new CompositeConfiguration();
        File configFile = new File(Paths.get(this.configFolderPath, this.configFilename).toUri());

        try {
            config.addConfiguration(new HierarchicalINIConfiguration(configFile));
            Iterator keys = config.getKeys();

            while (keys.hasNext()) {
                String key = (String) keys.next();
                configData.put(key, config.getString(key));
            }
        } catch (ConfigurationException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public String getParameter(String section, String name) {
        String key = section + "." + name;
        String value = configData.get(key);
        if (Objects.isNull(value)) {
            return value;
        } else {
            value = config.getString(key);
            if (Objects.isNull(value)) {
                configData.put(key, value);
            }

            return value;
        }
    }
}
