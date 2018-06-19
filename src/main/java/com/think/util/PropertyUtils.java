package com.think.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Property工具类
 *
 * @author veione
 * @date 2018年6月15日10:37:39
 */
public final class PropertyUtils {

    /**
     * 从给定的文件加载Properties文件
     *
     * @param file 给定的文件
     * @return 对应的属性文件对象
     */
    public static Properties load(String file) {
        Properties properties = new Properties();
        try {
            properties.load(PropertyUtils.class.getClass().getResourceAsStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
