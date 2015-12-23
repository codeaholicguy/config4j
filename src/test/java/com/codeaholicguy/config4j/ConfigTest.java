package com.codeaholicguy.config4j;

import junit.framework.TestCase;
import org.junit.Assert;

/**
 * @author hoangnn
 */
public class ConfigTest extends TestCase {

    public void testGetInstance() throws Exception {
        Config config = Config.getInstance();
        Assert.assertNotNull(config);
    }

    public void testGetParameter() throws Exception {
        String configFilename = "config.ini";
        String configFolderPath = "src/main/resources";

        Config config = Config.getInstance(configFolderPath, configFilename);
        Assert.assertEquals("127.0.0.1", config.getParameter("server_config", "host"));
    }
}