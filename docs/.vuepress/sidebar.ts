import {sidebar} from "vuepress-theme-hope";
import {aboutTheAuthor, books, databaseJson, javaJson, openSources, toolsJson, webJson,linuxJson} from "./sidebars";

/**
 * 应该把更精确的路径放置在前边
 */
export const sidebarConfig = sidebar({
    "/open-source/": openSources,
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
