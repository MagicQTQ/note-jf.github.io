/**
 * MQ 侧边栏json数据
 */
export const middlewareJson =
    {
        text: "中间件",
        icon: "tool",
        prefix: "middleware/",
        collapsable: true,
        children: [
            {
                text: "MQ中间件",
                icon: "tool",
                prefix: "mq/",
                collapsable: true,
                children: [
                    "mq-rabbit",
                    "mq-rocket",
                    "kafka",
                    "mq-active",
                ],
            },
            "nginx",
        ],
    }

