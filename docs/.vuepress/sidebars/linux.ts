/**
 * Linux 侧边栏json数据
 */
export const linuxJson =
    {
        text: "Linux&集群管理",
        icon: "linux",
        prefix: "linux/",
        collapsable: true,
        children: [
            "basis",
            "jenkins",
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
                    "ansible-jdk",
                    "ansible-docker",
                    "ansible-mysql",
                    "ansible-redis",
                    "ansible-nginx",
                ]
            },
            {
                text: "k8s",
                icon: "tool",
                prefix: "k8s/",
                collapsable: true,
                children: [
                    "basis",
                    "ingress",
                    "kube-sphere",
                    "pod-yaml",
                    "nfs-pv-pvc-config-secret",
                    "install-application",
                    "deploys",
                ]
            },
        ],
    }

