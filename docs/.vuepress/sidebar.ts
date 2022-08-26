// @ts-ignore
import {sidebar} from "vuepress-theme-hope";
import {
    allSources,
    aboutTheAuthor,
    architectures,
    books,
    databaseJson,
    javaJson,
    middlewareJson,
    linuxJson,
    kubernetesJson,
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
    "/all/": allSources,
    "/": [
        javaJson,
        databaseJson,
        webJson,
        middlewareJson,
        linuxJson,
        kubernetesJson,
        toolsJson
    ],
});
