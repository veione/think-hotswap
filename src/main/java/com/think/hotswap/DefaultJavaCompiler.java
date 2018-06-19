package com.think.hotswap;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import java.io.File;
import java.util.Collection;
import java.util.List;

/**
 * 默认Java编译器实现
 *
 * @version veione
 * @date 2018年6月15日11:45:47
 * @since 1.0
 */
public class DefaultJavaCompiler extends AbstractCompiler {

    @Override
    protected boolean doCompiler(Collection<File> files, List<String> options) {
        boolean flag;
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(files);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, options, null, compilationUnits);
        flag = task.call();
        return flag;
    }
}
