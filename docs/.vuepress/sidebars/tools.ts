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
            "maven",
            "idea",
            "nodejs-setting",
            "vagrant-vmware",
            {
                text: "database工具",
                icon: "database",
                prefix: "database/",
                collapsable: true,
                children: ["CHINER", "datagrip", "DBeaver","screw"],
            },
            {
                text: "Git",
                icon: "git",
                prefix: "git/",
                collapsable: true,
                children: ["git-setting", "git-intro", "github-tips"],
            },
            {
                text: "Docker",
                icon: "docker1",
                prefix: "docker/",
                collapsable: true,
                children: ["docker-note", "docker-install-mysql-redis-nginx-nacos-mq-es", "docker-intro", "docker-in-action"],
            }
        ],
    }

