package com.think.hotswap;

import com.think.util.FileUtils;
import com.think.util.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 热替换检测线程
 *
 * @author veione
 * @date 2018年6月15日11:05:06
 * @since 1.0
 */
public class WatchDog extends Thread {
    private final Logger logger = LoggerFactory.getLogger(WatchDog.class);
    /**
     * 默认检查时间 ： 5秒
     */
    public static final int DEFAULT_CHECK_DURATION = 5000;
    /**
     * 默认检查休眠时间：3秒
     */
    public static final int DEFAULT_SLEEP_DURATION = 6000;
    private volatile boolean active = true;
    private long lastCheckTime = System.currentTimeMillis();
    /**
     * 扫描编译源文件目录
     */
    private final String inPath;
    /**
     * 编译后的class存放目录
     */
    private final String outPath;
    /**
     * 源码编译器
     */
    private final ICompiler compiler;
    /**
     * 编译选项列表
     */
    private final List<String> options;
    /**
     * 类替换
     */
    private final Reloader reloader;

    private final ExecutorService executor = Executors.newFixedThreadPool(1);

    public WatchDog(String args, Instrumentation inst) {
        super("WatchDog");
        logger.info("Received arguments : {}", args);
        String[] inOutPath = StringUtils.getInOutPath(args);
        inPath = inOutPath[0];
        outPath = inOutPath[1];
        logger.info("Current in path = {}, out path = {}", inPath, outPath);
        compiler = new DefaultJavaCompiler();
        options = Arrays.asList("-d", outPath);
        reloader = new ClassReloader(outPath, inst);
        logger.info("(★^O^★) 初始化完成啦 :)");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> this.stopWatch()));
    }

    @Override
    public void run() {
        try {
            while (active) {
                logger.info("I',m running .... :)");
                long cur = System.currentTimeMillis();
                if ((cur - lastCheckTime) > DEFAULT_CHECK_DURATION) {
                    boolean finished = process();
                    if (finished) {
                        // 执行热替换操作
                        executor.execute(() -> {
                            try {
                                List<File> files = FileUtils.listFiles(outPath);
                                if (!files.isEmpty()) {
                                    reloader.reload(files);
                                }
                            } catch (ClassNotFoundException e) {
                                logger.error("Hotswap tips Class not found exception", e);
                            } catch (UnmodifiableClassException e) {
                                logger.error("Hotswap unsupported class schema modified", e);
                            }
                        });
                    }
                    lastCheckTime = cur;
                }
                Thread.sleep(DEFAULT_SLEEP_DURATION);
            }
        } catch (InterruptedException e) {
            logger.error("Thread interrupt exception", e);
        }
    }

    /**
     * 检查目录下面是否有热替换资源
     */
    private boolean process() {
        boolean flag = false;
        List<File> files = FileUtils.listFiles(inPath);
        try {
            if (!files.isEmpty()) {
                // 执行编译任务
                flag = compiler.compiler(files, options);
            }
        } finally {
            // 文件清理工作
            files.forEach(File::delete);
            files.clear();
            FileUtils.removeEmptyDir(new File(inPath), inPath);
        }
        return flag;
    }

    /**
     * 停止检测
     */
    public void stopWatch() {
        this.active = false;
    }
}
