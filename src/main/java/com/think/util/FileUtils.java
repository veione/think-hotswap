package com.think.util;

import java.io.File;
import java.util.List;
import java.util.LinkedList;

/**
 * 文件工具类
 *
 * @author veione
 * @date 2018年6月15日10:36:05
 */
public final class FileUtils {
    /**
     * 根据给定的目录列出该目录下面的所有文件
     *
     * @param directory 目录
     * @return 目录下面的所有文件集合
     */
    public static List<File> listFiles(String directory) {
        List<File> files = new LinkedList<>();
        File baseDirectory = new File(directory);
        if (baseDirectory.isDirectory()) {
            File[] directorys = baseDirectory.listFiles();
            for (File file : directorys) {
                if (file.isDirectory()) {
                    files.addAll(listFiles(file.getAbsolutePath()));
                } else {
                    files.add(file);
                }
            }
        }
        return files;
    }

    /**
     * 移除给定目录下面的所有空子目录
     *
     * @param dir      主目录
     * @param basePath 主目录需要留下来的目录
     */
    public static void removeEmptyDir(File dir, String basePath) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; i++) {
                removeEmptyDir(files[i], basePath);
            }
        }
        if (!basePath.contains(dir.getName())) {
            dir.delete();
        }
    }
}
