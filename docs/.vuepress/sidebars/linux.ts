/**
 * Linux 侧边栏json数据
 */
export const linuxJson =
    {
        text: "Linux",
        icon: "tool",
        prefix: "linux/",
        collapsable: true,
        children: [
            "basis",
            {
                text: "ansible运维管理工具",
                icon: "tool",
                prefix: "ansible/",
                collapsable: true,
                children: [
                    "ansible-basis",
                    "ansible",
                    "ansible-doc",
                    "ansible-playbook",
                    "ansible-galaxy",
                ]
            },
        ],
    }

