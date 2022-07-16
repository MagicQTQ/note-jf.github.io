---
icon: edit
title: maven管理
category: tools
date: 2020-01-01
tag:
- maven
---

# 打包

mvn clean

mvn compile

mvn package --

mvn install



.jar.original

> .jar.original 是普通jar包，不包含依赖
> .jar 是可执行jar包，包含了pom中的所有依赖，可以直接用java -jar 命令执行
> 如果是部署，就用.jar
> 如果是给别的项目用，就要给.jar.original这个包