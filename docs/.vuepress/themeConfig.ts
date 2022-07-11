import {hopeTheme} from "vuepress-theme-hope";
import {navbarConfig} from "./navbar";
import {sidebarConfig} from "./sidebar";

export default hopeTheme({
    // logo: "/logo.png",
    hostname: "https://note-jf.cn/",
    author: {
        name: "jf",
        // url: "https://note-jf.cn/article/",
        url: "/article",
    },
    repo: "https://gitee.com/cps007/notemd",
    darkmode: "toggle",/*toggle,auto*/
    fullscreen: true,
    themeColor: {
        blue: "#1d8be3",
        red: "#f26d6d",
        green: "#3eaf7c",
        orange: "#fb9b5f",
    },
    docsDir: "docs",
    iconAssets: "iconfont",
    iconPrefix: "iconfont icon-",
    pure: true,
    navbar: navbarConfig,
    sidebar: sidebarConfig,
    pageInfo: ["Author", "Category", "Tag", "Date", "Original", "Word", "PageView", "ReadingTime"],
    blog: {
        intro: "/about-the-author/",
        sidebarDisplay: "mobile",
        medias: {
            Zhihu: "https://www.zhihu.com/people/tops6",
            Github: "https://github.com/note-jf",
            Gitee: "https://gitee.com/cps007/notemd",
        },
    },
    footer: '<a href="https://beian.miit.gov.cn/" target="_blank">黔ICP备2022xxxxxx号-1</a>',
    displayFooter: true,
    plugins: {
        mdEnhance: {
            //增强语法
            enableAll: true,
            // @ts-ignore
            include: true,
            //流程图
            flowchart: true,
            //脚注
            footnote: true,
            //自定义容器:提示、注释、信息、注意、警告和详情
            container: true,
            // 启用图片标记
            imageMark: true,
            // 启用图片大小
            imageSize: true,
        },
        copyCode: {
            showInMobile: true
        },
        // @ts-ignore
        photoSwipe: true,
        blog: {
            autoExcerpt: true,
        },
        feed: {
            json: true,
        },
        pwa: {
            maxSize: 1024 * 6,
            cachePic: true,
            maxPicSize: 1024 * 4,
            cacheHTML: true,
        },
    },
});