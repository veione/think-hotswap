package com.think.util;

import java.io.IOException;
import java.io.File;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;

/**
 * IO工具类
 *
 * @author veione
 * @since 2018年6月15日10:36:22
 */
public final class IOUtils {
    public static final int EOF = -1;

    /**
     * 将文件对象转换为字节流对象
     *
     * @param file 文件对象
     * @return
     */
    public static byte[] toByteArray(File file) {
        if (file.isFile() && file.canRead()) {
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length())) {
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
                    int buf_size = 1024;
                    byte[] buffer = new byte[buf_size];
                    int len = 0;
                    while (EOF != (len = in.read(buffer, 0, buf_size))) {
                        bos.write(buffer, 0, len);
                    }
                    return bos.toByteArray();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw e;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
