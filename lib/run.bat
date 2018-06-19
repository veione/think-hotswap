rem 关闭命令回显
@echo off
rem 设置标题
title Think-HotSwap 1.0.0
rem 设置文字前景色为绿色
color 2
rem 执行java命令
java -javaagent:think-hotswap-1.0.0.jar=in=hotswap/in;out=hotswap/out -Xrunjdwp:transport=dt_socket,server=y,address=7899,suspend=n -jar think-test.jar
rem 暂停
pause
