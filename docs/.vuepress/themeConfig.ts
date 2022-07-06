import {hopeTheme} from "vuepress-theme-hope";
import {navbarConfig} from "./navbar";
import {sidebarConfig} from "./sidebar";
// @ts-ignore
import {defineUserConfig} from "vuepress";

export default hopeTheme({
    logo: "/logo.png",
    hostname: "https://javajf.cn/",
    author: {
        name: "jf",
        url: "https://javajf.cn/article/",
    },
    repo: "https://gitee.com/cps007/notemd",
    docsDir: "docs",
    iconPrefix: "iconfont icon-",
    pure: true,
    navbar: navbarConfig,
    sidebar: sidebarConfig,
    pageInfo: ["Author", "Category", "Tag", "Date", "Original", "Word"],
    blog: {
        intro: "/about-the-author/",
        sidebarDisplay: "mobile",
        medias: {
            Zhihu: "https://www.zhihu.com/people/tops6",
            Github: "https://github.com/",
            Gitee: "https://gitee.com/cps007/notemd",
        },
    },
    footer:
        '<a href="https://beian.miit.gov.cn/" target="_blank">黔ICP备2022xxxxxx号-1</a>',
    displayFooter: true,
    plugins: {
        blog: {
            autoExcerpt: true,
        },
        mdEnhance: {
            tasklist: true,
        },
        feed: {
            json: true,
        },
        // comment: {
        //   type: "giscus",
        //   repo: "cps007/notemd",
        //   repoId: "MDEwOlJlcG9zaXRvcnkxMzI0NjQzOTU=",
        //   category: "Announcements",
        //   categoryId: "DIC_kwDOB-U_C84COYQF",
        // },
        // @ts-ignore
        search: {
            // https://v2.vuepress.vuejs.org/zh/reference/plugin/search.html
            // 排除首页
            isSearchable: (page) => page.path !== "/",
            maxSuggestions: 10,
            hotKeys: ["s", "/"],
            // 用于在页面的搜索索引中添加额外字段
            getExtraFields: () => [],
            locales: {
                "/": {
                    placeholder: "搜索",
                },
            },
        },
        // pwa: {
        //   favicon: "/favicon.ico",
        //   cachePic: true,
        //   apple: {
        //     icon: "/assets/icon/apple-icon-152.png",
        //     statusBarColor: "black",
        //   },
        //   msTile: {
        //     image: "/assets/icon/ms-icon-144.png",
        //     color: "#ffffff",
        //   },
        //   manifest: {
        //     icons: [
        //       {
        //         src: "/assets/icon/chrome-mask-512.png",
        //         sizes: "512x512",
        //         purpose: "maskable",
        //         type: "image/png",
        //       },
        //       {
        //         src: "/assets/icon/chrome-mask-192.png",
        //         sizes: "192x192",
        //         purpose: "maskable",
        //         type: "image/png",
        //       },
        //       {
        //         src: "/assets/icon/chrome-512.png",
        //         sizes: "512x512",
        //         type: "image/png",
        //       },
        //       {
        //         src: "/assets/icon/chrome-192.png",
        //         sizes: "192x192",
        //         type: "image/png",
        //       },
        //     ],
        //     shortcuts: [
        //       {
        //         name: "jf",
        //         short_name: "jf",
        //         url: "/guide/",
        //         icons: [
        //           {
        //             src: "/assets/icon/guide-maskable.png",
        //             sizes: "192x192",
        //             purpose: "maskable",
        //             type: "image/png",
        //           },
        //           {
        //             src: "/assets/icon/guide-monochrome.png",
        //             sizes: "192x192",
        //             purpose: "monochrome",
        //             type: "image/png",
        //           },
        //         ],
        //       },
        //     ],
        //   },
        // },
    },
})
