package com.think.hotswap;

import java.io.File;
import java.lang.instrument.UnmodifiableClassException;
import java.util.List;

public interface Reloader {
    /**
     * 将编译的类进行热替换操作
     *
     * @param files 文件集合
     */
    void reload(List<File> files) throws ClassNotFoundException, UnmodifiableClassException;
}
