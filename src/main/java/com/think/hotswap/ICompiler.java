package com.think.hotswap;

import java.io.File;
import java.util.Collection;
import java.util.List;

/**
 * 编译接口类
 *
 * @author veione
 * @date 2018年6月15日11:40:58
 * @since 1.0
 */
public interface ICompiler {
    /**
     * 批量遍历源文件
     *
     * @param files   源文件集合
     * @param options 编译选项列表
     * @return 如果编译成功则返回true, 失败则返回false
     */
    boolean compiler(Collection<File> files, List<String> options);
}
