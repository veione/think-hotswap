# think-hotswap
一个简单的Java类替换工具

## 简介
本工具采用Java内部提供的Instrumentation来实现类的热替换操作，故不支持修改类结构与类方法的操作，但是能在不修改类结构和方法的情况下，简单的修改其方法内部的一些实现，所以支持小部分的修改，比如在我们日常的开发中，我们的项目已经上线了，但是因为有些逻辑错误问题需要修改，这时候这个工具就派上用场啦，简单修改下，然后将修改好的源文件放在指定的热替换目录下该工具就会将源文件进行编译成class文件然后进行热替换操作，这样就能做到不重启服务器而做到热替换的功能了。

## 使用指南
本工具的使用比较简单，下面就详细介绍一下这个工具的使用指南。

- 首先在目录下面找到lib目录，然后找到**think-hotswap-1.0.0.jar**这个文件，然后将其移动到里项目的**lib**目录下面。
- 在启动的命令行中指定其：**java -javaagent:think-hotswap-1.0.0.jar=in=hotswap/in;out=hotswap/out**,
详细的使用可以参考**lib**目录下面的**run.bat**文件，里面有详细的命令参数，可以通过简单的修改并可以使用了。
请注意**-javaagent:think-hotswap-1.0.0.jar=in=hotswap/in;out=hotswap/out** 这个参数，首先-javaagent:think-hotswap-1.0.0.jar指定了其代理jar包对象，后面**=in=hotswap/in;out=hotswap/out** 相当于指定了参数，分别是需要替换源码的位置和源码编译输出的位置，这里我们使用了**;** 号来分割两个参数，之后我们将需要替换的源代码文件放入到/hotswap/in/目录下即可，该工具会自动编译文件输出到/hotswap/out/目录下，然后进行其热替换之后会自动将文件进行删除，具体使用可以参考其demo下面的演示。


## 界面预览

![项目目录](/snapshot/project_directory.png)

![运行工具](/snapshot/tools_running.png)

![热替换](/snapshot/Hotswap.png)


## 参考:
[Java 类的热替换 —— 概念、设计与实现](https://www.ibm.com/developerworks/cn/java/j-lo-hotswapcls/)

[Java SE 6 新特性 Instrumentation 新功能](https://www.ibm.com/developerworks/cn/java/j-lo-jse61/index.html)
