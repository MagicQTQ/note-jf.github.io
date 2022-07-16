// @ts-ignore
import {sidebar} from "vuepress-theme-hope";
import {
    aboutTheAuthor,
    architectures,
    books,
    databaseJson,
    javaJson,
    mqJson,
    linuxJson,
    openSources,
    toolsJson,
    webJson
} from "./sidebars";

/**
 * 应该把更精确的路径放置在前边
 */
export const sidebarConfig = sidebar({
    "/history/": ["history"],
    // @ts-ignore
    "/about-the-author/": aboutTheAuthor,
    "/architecture/": architectures,
    "/books/": books,
    "/open-source/": openSources,
    "/": [
        javaJson,
        databaseJson,
        webJson,
        mqJson,
        linuxJson,
        toolsJson
    ],
});
