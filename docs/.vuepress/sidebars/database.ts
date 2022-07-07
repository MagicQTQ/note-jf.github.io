/**
 * Java 侧边栏json数据
 */
export const databaseJson =
    {
        text: "数据库",
        icon: "tool",
        prefix: "database/",
        collapsable: true,
        children: [
            {
                text: "MySQL",
                icon: "tool",
                prefix: "mysql/",
                collapsable: true,
                children: [
                    "MySQL",
                ]
            },
            {
                text: "oracle",
                icon: "tool",
                prefix: "oracle/",
                collapsable: true,
                children: [
                ]
            },
            {
                text: "redis",
                icon: "tool",
                prefix: "redis/",
                collapsable: true,
                children: [
                ]
            }
        ],
    }

