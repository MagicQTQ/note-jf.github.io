/**
 * Java 侧边栏json数据
 */
export const toolsJson =
    {
        text: "开发工具",
        icon: "tool",
        prefix: "tools/",
        collapsable: true,
        children: [
            {
                text: "Git",
                icon: "git",
                prefix: "git/",
                collapsable: true,
                children: ["git-intro", "github-tips"],
            },
            {
                text: "Docker",
                icon: "docker1",
                prefix: "docker/",
                collapsable: true,
                children: ["docker-intro", "docker-in-action"],
            }
        ],
    }

