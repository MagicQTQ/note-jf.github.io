md笔记

## 目录结构

```text
├─ docs //md 文件系统 
│  ├─ .vuepress             // vuepress 项目 
│  │  ├─ sidebar            // vuepress 侧边栏json数据设置 
│  │  │  └─ xx.ts 
│  │  ├─ styles             // vuepress css样式配置 
│  │  │  └─ xx.scss 
│  │  ├─ navbar.ts          // vuepress 导航栏配置 
│  │  ├─ sidebar.ts         // vuepress 侧边栏、路由配置 
│  │  ├─ themeConfig.ts     // vuepress 主题配置 
│  │  └─ config.ts          // vuepress 项目配置 
│  │   
│  │  ===================下面开始就是md文件，可以包含多级目录=================== 
│  ├─ java 
│  │  ├─ basis              // java 基础笔记 
│  │  │  └─ basis.md 
│  │  ├─ jvm                // jvm 
│  │  │  └─ jvm.md 
│  │  └─ xx.md 
│  ├─ mysql                 // mysql 笔记 
│  ├─ linux                 // linux 笔记 
│  ├─ web                   // 前端笔记 
│  └─ README.md             // 首页
├─ .gitignore               //git 配置 
└─ package.json             // 依赖

```

## 技术选型

<a href="https://www.npmjs.com/" target="_blank">npm 版本查找</a>

<a href="https://vuepress-theme-hope.github.io/v2/zh/" target="_blank">vuepress-theme-hope</a>

| 技术 | 版本 |
| ----- | ----- |
| @vuepress/plugin-search  | <a href="https://www.npmjs.com/package/@vuepress/plugin-search" target="_blank">^2.0.0-beta.38</a>  |
| vuepress-theme-hope | <a href="https://www.npmjs.com/package/vuepress-theme-hope" target="_blank">^2.0.0-beta.36</a>  |
| vuepress-vite | <a href="https://www.npmjs.com/package/vuepress-vite" target="_blank">2.0.0-beta.38</a>  |
| vuepress-webpack | <a href="https://www.npmjs.com/package/vuepress-webpack" target="_blank">2.0.0-beta.38</a>  |

* [项目介绍](./docs/javajf/intro.md)
* [常见问题](./docs/javajf/faq.md)

## Java

### 基础

**知识点/面试题总结** : (必看:+1: )：

1. [Java 基础常见知识点&面试题总结(上)](docs/java/basis/java-basic-questions-01.md)

**重要知识点详解** ：

* [为什么 Java 中只有值传递？](docs/java/basis/why-there-only-value-passing-in-java.md)
