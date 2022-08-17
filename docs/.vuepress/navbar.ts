import {navbar} from "vuepress-theme-hope";

export const navbarConfig = navbar([
    "/",
    "/home",
    {text: "面试宝典", icon: "book", link: "/all/all"},
    {text: "架构专区", icon: "edit", link: "/architecture/architecture"},
    {text: "开源专区", icon: "github", link: "/open-source/open-source"},
    {text: "技术书籍", icon: "book", link: "/books/books"},
    {
        text: "个人中心",
        icon: "anonymous",
        children: [
            {text: "文章", icon: "note", link: "/article"},
            {text: "分类", icon: "categoryselected", link: "/category"},
            {text: "标签", icon: "list", link: "/tag"},
            {text: "加密文章", icon: "command", link: "/encrypted"},
            {text: "收藏文章", icon: "like", link: "/star"},
            {text: "时间线", icon: "time", link: "/timeline"},
        ],
    },
    {
        text: "网站相关",
        icon: "about",
        children: [
            {text: "关于作者", icon: "zuozhe", link: "/about-the-author/about-the-author"},
            {
                text: "网站历史",
                icon: "time",
                link: "/history/history",
            },
        ],
    },
]);
