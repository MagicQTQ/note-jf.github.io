// @ts-ignore
import {hopeTheme} from "vuepress-theme-hope";
import {navbarConfig} from "./navbar";
import {sidebarConfig} from "./sidebar";
import { path } from "@vuepress/utils";

export default hopeTheme({
    logo: "/logo.png",
    hostname: "https://note-jf.gitee.io/",
    author: {
        name: "jf",
        // 根据自己的域名设置
        //url: "https://note-jf.cn/article/",
        url: "/article",
    },
    repo: "https://gitee.com/note-jf/note-jf.gitee.io",
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
        intro: "/about-the-author/about-the-author",
        sidebarDisplay: "mobile",
        medias: {
            Gitee: "https://gitee.com/note-jf/note-jf.gitee.io",
            Github: "https://github.com/note-jf/note-jf.github.io",
            Zhihu: "https://www.zhihu.com/people/tops6",
            OSChina: [
                "https://my.oschina.net/jinfang",
                "<svg width=\"26px\" height=\"26px\" viewBox=\"0 0 32 32\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">\n" +
                "    <g id=\"Page-1\" stroke=\"none\" stroke-width=\"1\" fill=\"none\" fill-rule=\"evenodd\">\n" +
                "        <g id=\"Artboard\" transform=\"translate(-12.000000, -7.000000)\">\n" +
                "            <g id=\"logo\" transform=\"translate(13.000000, 8.000000)\">\n" +
                "                <g id=\"C\">\n" +
                "                    <path d=\"M14.8015997,20.6197183 C11.6832252,20.6408451 9.14153634,18.6126761 8.9706665,14.8309859 C8.79979666,11.3028169 11.9181712,8.93661972 14.8870346,8.87323944 C18.3044314,8.78873239 20.3548695,12.6760563 20.3548695,12.6760563 L29.1760248,9.44366197 C29.1760248,9.44366197 25.5450408,0.0845070423 15.7200251,0.0845070423 C6.53577131,0.0845070423 0.192228568,6.42253521 0.192228568,15.0211268 C0.192228568,22.6690141 6.1726729,30.2957746 15.5064378,29.9577465 C25.7586281,29.5774648 29.2614598,20.5985915 29.2614598,20.5985915 L20.2053584,17.6197183 C20.2267171,17.5774648 18.3685076,20.6197183 14.8015997,20.6197183\" id=\"Path\" fill=\"#3DAB53\"/>\n" +
                "                    <path d=\"M14.8015997,20.6197183 C11.6832252,20.6408451 9.14153634,18.6126761 8.9706665,14.8309859 C8.79979666,11.3028169 11.9181712,8.93661972 14.8870346,8.87323944 C18.3044314,8.78873239 20.3548695,12.6760563 20.3548695,12.6760563 L29.1760248,9.44366197 C29.1760248,9.44366197 25.5450408,0.0845070423 15.7200251,0.0845070423 C6.53577131,0.0845070423 0.192228568,6.42253521 0.192228568,15.0211268 C0.192228568,22.6690141 6.1726729,30.2957746 15.5064378,29.9577465 C25.7586281,29.5774648 29.2614598,20.5985915 29.2614598,20.5985915 L20.2053584,17.6197183 C20.2267171,17.5774648 18.3685076,20.6197183 14.8015997,20.6197183\" id=\"Path\" stroke=\"#006838\" stroke-width=\"1.05941556\"/>\n" +
                "                </g>\n" +
                "            </g>\n" +
                "        </g>\n" +
                "    </g>\n" +
                "</svg>",
            ],
        },
    },
    footer: '<a href="https://beian.miit.gov.cn/" target="_blank">黔ICP备2022xxxxxx号-1</a>',
    displayFooter: true,
    //加密文章
    encrypt: {
        config: {
            "/guide/encrypt.html": ["1234"],
        },
    },
    plugins: {
        mdEnhance: {
            mark: true,
            mermaid: true,
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
            showInMobile: true,
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
            maxSize: 1024 * 20,
            maxPicSize: 1024 * 10,
            cachePic: true,
            cacheHTML: true,
        },
    },
});