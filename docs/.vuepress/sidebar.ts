import {sidebar} from "vuepress-theme-hope";
import {aboutTheAuthor} from "./sidebars/about-the-author";
import {books} from "./sidebars/books";
import {openSources} from "./sidebars/open-source";
import {toolsJson} from "./sidebars/tools";
import {javaJson} from "./sidebars/java";

/**
 * 应该把更精确的路径放置在前边
 */
export const sidebarConfig = sidebar({
    "/open-source/": openSources,
    "/books/": books,
    // @ts-ignore
    "/about-the-author/": aboutTheAuthor,
    "/javajf/": ["history"],
    "/": [
        javaJson,
        toolsJson
    ],
});
