package com.think.util;

import java.io.File;
import java.util.Objects;

/**
 * 字符串工具类
 *
 * @author veione
 * @date 2018年6月15日12:27:34
 * @since 1.0
 */
public final class StringUtils {

    /**
     * 获取类全限定名
     *
     * @param fileName 文件路径名
     * @param basePath 主路径名
     * @return 类文件全限定名
     */
    public static String getFullClassName(String fileName, String basePath) {
        File baseFile = new File(basePath);
        String absPath = baseFile.getAbsolutePath();
        String clsPath = fileName.substring(absPath.length() + 1).replaceAll("[/\\\\]", ".");
        String clsFullName = clsPath.substring(0, clsPath.lastIndexOf("."));
        return clsFullName;
    }


    /**
     * 获取热替换输入路径和输出路径
     *
     * @param path 命令行给定的路径
     * @return 输入/输出路径数组
     */
    public static String[] getInOutPath(String path) {
        String errMsg = "Invalid in or out path, the path must like this -javaagent:foo.jar=in=/path/in;out=/path/out";
        Objects.requireNonNull(path, errMsg);
        int index = path.indexOf(";");
        String[] inOutPath = path.split(";");
        if (index == -1 || inOutPath.length < 2) {
            throw new IllegalArgumentException(errMsg);
        }
        String in = inOutPath[0];
        String out = inOutPath[1];
        in = in.substring(3);
        out = out.substring(4);
        inOutPath[0] = in;
        inOutPath[1] = out;
        return inOutPath;
    }
}
