import {hopeTheme} from "vuepress-theme-hope";
import {navbarConfig} from "./navbar";
import {sidebarConfig} from "./sidebar";

export default hopeTheme({
    // logo: "/logo.png",
    hostname: "https://javajf.cn/",
    author: {
        name: "jf",
        // url: "https://javajf.cn/article/",
        url: "/article/",
    },
    repo: "https://gitee.com/cps007/notemd",
    darkmode: "toggle",
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
    pageInfo: ["Author", "Category", "Tag", "Date", "Original", "Word", "PageView"],
    blog: {
        intro: "/about-the-author/",
        sidebarDisplay: "mobile",
        medias: {
            Zhihu: "https://www.zhihu.com/people/tops6",
            Github: "https://github.com/",
            Gitee: "https://gitee.com/cps007/notemd",
        },
    },
    footer: '<a href="https://beian.miit.gov.cn/" target="_blank">黔ICP备2022xxxxxx号-1</a>',
    displayFooter: true,
    plugins: {
        mdEnhance: {
            //增强语法
            enableAll: true,
            tasklist: true,
            //流程图
            flowchart: true,
            //脚注
            footnote: true,
            //自定义容器:提示、注释、信息、注意、警告和详情
            container: true,
        },
        copyCode: {},
        photoSwipe: true,
        blog: {
            autoExcerpt: true,
        },
        feed: {
            json: true,
        },
    },
})
