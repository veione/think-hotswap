package com.think.hotswap;

import com.think.util.FileUtils;
import com.think.util.IOUtils;
import com.think.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.List;

/**
 * ClassReloader实现类
 *
 * @author veione
 * @date 2018年6月15日12:16:45
 * @since 1.0
 */
public class ClassReloader implements Reloader {
    private final Logger logger = LoggerFactory.getLogger(ClassReloader.class);
    private final Instrumentation inst;
    private final String basePath;

    public ClassReloader(String basePath, Instrumentation inst) {
        this.basePath = basePath;
        this.inst = inst;
    }

    @Override
    public void reload(List<File> files) throws ClassNotFoundException, UnmodifiableClassException {
        if (inst != null) {
            if (inst.isRedefineClassesSupported()) {
                int size = files.size();
                try {
                    ClassDefinition[] definitions = new ClassDefinition[size];
                    for (int i = 0; i < size; i++) {
                        File file = files.get(i);
                        // 1：拿到全限定类名    2：拿到文件字节流对象
                        String clsFullName = StringUtils.getFullClassName(file.toString(), basePath);
                        logger.info("Load class {}", clsFullName);
                        Class<?> cls = Class.forName(clsFullName);
                        byte[] bytes = IOUtils.toByteArray(file);
                        if (bytes != null) {
                            ClassDefinition definition = new ClassDefinition(cls, bytes);
                            definitions[i] = definition;
                        }
                    }
                    inst.redefineClasses(definitions);
                    logger.info("Reload class successfully.");
                } finally {
                    // 清理掉加载过得资源
                    files.forEach(File::delete);
                    files.clear();
                    FileUtils.removeEmptyDir(new File(basePath), basePath);
                }
            } else {
                throw new UnsupportedOperationException("Unsupported class reload operation.");
            }
        }
    }
}
