package com.codeaholicguy.config4j.util;

import org.apache.commons.lang.StringUtils;

/**
 * @author hoangnn
 */
public class KeyUtil {

    public static String generateKey(String... strings) {
        return StringUtils.join(strings, "_");
    }

}
