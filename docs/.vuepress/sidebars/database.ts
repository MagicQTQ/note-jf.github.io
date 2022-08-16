/**
 * Java 侧边栏json数据
 */
export const databaseJson =
    {
        text: "数据库",
        icon: "mysql",
        prefix: "database/",
        collapsable: true,
        children: [
            {
                text: "MySQL",
                icon: "mysql",
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
                icon: "redis",
                prefix: "redis/",
                collapsable: true,
                children: [
                ]
            }
        ],
    }

