md笔记

```text
├─ docs //md 文件系统 
│ ├─ .vuepress // vuepress 项目 
│ │ ├─ sidebar // vuepress 侧边栏json数据设置 
│ │ │ └─ xx.ts 
│ │ ├─ styles // vuepress css样式配置 
│ │ │ └─ xx.scss 
│ │ ├─ navbar.ts // vuepress 导航栏配置 
│ │ ├─ sidebar.ts // vuepress 侧边栏配置 
│ │ ├─ themeConfig.ts // vuepress 主题配置 
│ │ └─ config.ts // vuepress 项目配置 
│ │  
│ │ ===================下面开始就是md文件，可以包含多级目录=================== 
│ ├─ java 
│ │ ├─ basis // java 基础笔记 
│ │ │ └─ basis.md 
│ │ ├─ jvm // jvm 
│ │ │ └─ jvm.md 
│ │ └─ xx.md 
│ ├─ mysql // mysql 笔记 
│ ├─ linux // linux 笔记 
│ ├─ web // 前端笔记 
│ └─ README.md // 首页 
├─ .gitignore //git 配置 
└─ package.json // 依赖

```


<div align="center">
    <p>
        <a href="https://gitee.com/cps007/notemd" target="_blank">
            <img src="https://img-blog.csdnimg.cn/img_convert/1c00413c65d1995993bf2b0daf7b4f03.png#pic_center" width="" />
        </a>
    </p>
    <p>
        <a href="https://javajf.cn/"><img src="https://img.shields.io/badge/阅读-read-brightgreen.svg" alt="阅读" /></a>
        <img src="https://img.shields.io/github/stars/cps007/JavaGuide" alt="stars" />
        <img src="https://img.shields.io/github/forks/cps007/JavaGuide" alt="forks" />
        <img src="https://img.shields.io/github/issues/cps007/JavaGuide" alt="issues" />
    </p>
    <p>
        <a href="https://gitee.com/cps007/notemd">Github</a> |
        <a href="https://gitee.com/cps007/JavaGuide">Gitee</a>
    </p>  
</div>

> 1. **面试专版** ：准备面试的小伙伴可以考虑面试专版：[《Java 面试进阶指北 》](https://www.yuque.com/docs/share/f37fc804-bfe6-4b0d-b373-9c462188fec7) (质量很高，专为面试打造，配合 JavaGuide 食用)。
> 1. **知识星球** ：专属面试小册/一对一交流/简历修改/专属求职指南，欢迎加入 [JavaGuide 知识星球](https://www.yuque.com/docs/share/8a30ffb5-83f3-40f9-baf9-38de68b906dc)（点击链接即可查看星球的详细介绍，一定一定一定确定自己真的需要再加入，一定一定要看完详细介绍之后再加我）。
> 2. **转载须知** ：以下所有文章如非文首说明为转载皆为我（Guide 哥）的原创，转载在文首注明出处，如发现恶意抄袭/搬运，会动用法律武器维护自己的权益。让我们一起维护一个良好的技术创作环境！

你可能需要：

* [项目介绍](./docs/javaguide/intro.md)
* [贡献指南](./docs/javaguide/contribution-guideline.md)
* [常见问题](./docs/javaguide/faq.md)
* [项目代办.md](./docs/javaguide/todo)

## Java

### 基础

**知识点/面试题总结** : (必看:+1: )：

1. [Java 基础常见知识点&面试题总结(上)](docs/java/basis/java-basic-questions-01.md)
2. [Java 基础常见知识点&面试题总结(中)](docs/java/basis/java-basic-questions-02.md)
3. [Java 基础常见知识点&面试题总结(下)](docs/java/basis/java-basic-questions-03.md)

**重要知识点详解** ：

* [为什么 Java 中只有值传递？](docs/java/basis/why-there-only-value-passing-in-java.md)
* [Java 序列化详解](docs/java/basis/serialization.md)
* [泛型&序列化详解](docs/java/basis/generics-and-wildcards.md)
* [反射机制详解](docs/java/basis/reflection.md)
* [Java 代理模式详解](docs/java/basis/proxy.md)
* [IO 模型详解](docs/java/basis/io.md)
* [BigDecimal 详解](docs/java/basis/bigdecimal.md)
