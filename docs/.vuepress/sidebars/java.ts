/**
 * Java 侧边栏json数据
 */
export const javaJson =
    {
        text: "Java",
        icon: "java",
        prefix: "java/",
        collapsable: true,
        children: [
            {
                text: "基础",
                prefix: "basis/",
                icon: "basic",
                collapsable: true,
                children: [
                    "java-basic-questions-01",
                ],
            },
            {
                text: "集合",
                prefix: "collection/",
                icon: "container",
                collapsable: true,
                children: [
                    "java-collection-questions-01",
                ],
            },
            {
                text: "新特性",
                prefix: "new-features/",
                icon: "featured",
                collapsable: true,
                children: [
                    "java8-common-new-features",
                    "java8-tutorial-translate",
                    "java9",
                    "java10",
                    "java11",
                    "java12-13",
                    "java14-15",
                    "java16",
                ],
            },
        ],
    }
