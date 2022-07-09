// @ts-ignore
import {sidebar} from "vuepress-theme-hope";
import {
    aboutTheAuthor,
    architectures,
    books,
    databaseJson,
    javaJson,
    linuxJson,
    openSources,
    toolsJson,
    webJson
} from "./sidebars";

/**
 * 应该把更精确的路径放置在前边
 */
export const sidebarConfig = sidebar({
    "/open-source/": openSources,
    "/architecture/": architectures,
    "/books/": books,
    // @ts-ignore
    "/about-the-author/": aboutTheAuthor,
    "/about/": ["history"],
    "/": [
        javaJson,
        databaseJson,
        webJson,
        linuxJson,
        toolsJson
    ],
});
