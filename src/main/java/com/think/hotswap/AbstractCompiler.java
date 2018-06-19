package com.think.hotswap;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 * 抽象编译器
 *
 * @author veione
 * @date 2018年6月15日11:43:15
 * @since 1.0
 */
public abstract class AbstractCompiler implements ICompiler {
    protected final JavaCompiler compiler;
    protected final StandardJavaFileManager fileManager;

    public AbstractCompiler() {
        this.compiler = ToolProvider.getSystemJavaCompiler();
        this.fileManager = compiler.getStandardFileManager(null, Locale.CHINA, Charset.defaultCharset());
    }

    @Override
    public boolean compiler(Collection<File> files, List<String> options) {
        return doCompiler(files, options);
    }

    protected abstract boolean doCompiler(Collection<File> files, List<String> options);
}
