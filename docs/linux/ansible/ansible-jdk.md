---
icon: edit
title: ansible安装jdk
category: linux
date: 2022-07-30
tag:
- ansible
---


# ansible安装jdk

### 设置主机清单 `vim /etc/ansible/hosts`

```
[jdk]
192.168.0.[8:10]
```



### ① 在`roles`目录下生成对应的目录结构

```shell
[root@admin roles]# ansible-galaxy role init jdk
- Role jdk was created successfully

[root@admin roles]# ls
jdk  jdk.yml

[root@admin roles]# cat jdk.yml
---
- hosts: jdk
  remote_user: root
  roles:
    - jdk

[root@admin roles]# tree dockekr/
jdk/
├── files
│   ├── jdk-17_linux-x64_bin.rpm
│   └── uni_jdk.sh
├── tasks
│   ├── install.yml
│   └── main.yml
└── vars
    └── main.yml

8 directories, 8 files
```

### ② 定义 tasks 任务文件

wget https://download.oracle.com/java/17/latest/jdk-17_linux-x64_bin.rpm

vim /etc/ansible/roles/jdk/tasks/main.yml

```yaml
---
- include: install.yml
```

**install.yml**

```yaml
---
- name: uni old jdk
  script: ../files/uni_jdk.sh
- name: copy package to jdk17
  copy: src=../files/jdk-17_linux-x64_bin.rpm dest=/opt mode=0775
- name: delete line
  lineinfile: dest=/etc/profile regexp='(.*)JAVA_HOME(.*)' state=absent  
- name: install jdk17
  shell: rpm -ivh /opt/jdk-17_linux-x64_bin.rpm
- name: set jdk17 env
  lineinfile: dest=/etc/profile line="{{item.value}}" state=present
  with_items:
  - {value: "export JAVA_HOME=/usr/java/jdk-17.0.4/"}
  - {value: "export PATH=$JAVA_HOME/bin:$PATH"}
  
- name: source profile
  shell: source /etc/profile
- name: remove jdk package
  shell: rm -rf /opt/jdk-17_linux-x64_bin.rpm
```



### ③ 编写安装 shell 脚本

vim files/uni_jdk.sh

`rpm -qa | grep java`

```shell
#!/bin/bash
# 卸载
rpm -e --nodeps java-1.8.0-openjdk-headless
rpm -e --nodeps java-1.8.0-openjdk
rpm -e --nodeps java-1.7.0-openjdk-headless
rpm -e --nodeps java-1.7.0-openjdk
```



### ③ 定义 jdk 剧本文件

```yaml
[root@admin roles]# vim ./jdk.yml
---
- hosts: jdk
  remote_user: root
  roles:
    - jdk

```

### ⑥ 启动剧本

剧本定义完成以后，我们就可以来启动服务了：

```shell
[root@admin roles]# ansible-playbook jdk.yml
PLAY [jdk] *******************************

TASK [Gathering Facts] *****************************
ok: [192.168.0.8]
ok: [192.168.0.10]
ok: [192.168.0.9]

TASK [uni old jdk] ***************************
changed: [192.168.0.10]
changed: [192.168.0.9]
changed: [192.168.0.8]

TASK [copy package to jdk17] ********************************
changed: [192.168.0.8]
changed: [192.168.0.10]
changed: [192.168.0.9]

TASK [jdk : delete line] **************************
ok: [192.168.0.9]
ok: [192.168.0.10]
ok: [192.168.0.8]

TASK [install jdk17] **********************************
changed: [192.168.0.9]
changed: [192.168.0.10]
changed: [192.168.0.8]

TASK [set jdk17 env] *********************************
changed: [192.168.0.9] => (item={u'value': u'export JAVA_HOME=/usr/java/jdk-17.0.4/'})
changed: [192.168.0.10] => (item={u'value': u'export JAVA_HOME=/usr/java/jdk-17.0.4/'})
changed: [192.168.0.8] => (item={u'value': u'export JAVA_HOME=/usr/java/jdk-17.0.4/'})
changed: [192.168.0.9] => (item={u'value': u'export PATH=$JAVA_HOME/bin:$PATH'})
changed: [192.168.0.10] => (item={u'value': u'export PATH=$JAVA_HOME/bin:$PATH'})
changed: [192.168.0.8] => (item={u'value': u'export PATH=$JAVA_HOME/bin:$PATH'})

TASK [jdk : source profile] **********************************************************************************************
changed: [192.168.0.8]
changed: [192.168.0.9]
changed: [192.168.0.10]

TASK [remove jdk package] ************************************************************************************************
changed: [192.168.0.8]
changed: [192.168.0.10]
changed: [192.168.0.9]

PLAY RECAP ***************************************************************************************************************
192.168.0.10               : ok=8    changed=6    unreachable=0    failed=0    skipped=0    rescued=0    ignored=0
192.168.0.8                : ok=8    changed=6    unreachable=0    failed=0    skipped=0    rescued=0    ignored=0
192.168.0.9                : ok=8    changed=6    unreachable=0    failed=0    skipped=0    rescued=0    ignored=0
```

校验：ansible jdk -m shell -a 'java -version'

```shell

[root@admin roles]# ansible jdk -m shell -a 'java -version'
192.168.0.8 | CHANGED | rc=0 >>
java version "17.0.4" 2022-07-19 LTS
Java(TM) SE Runtime Environment (build 17.0.4+11-LTS-179)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.4+11-LTS-179, mixed mode, sharing)

192.168.0.10 | CHANGED | rc=0 >>
java version "17.0.4" 2022-07-19 LTS
Java(TM) SE Runtime Environment (build 17.0.4+11-LTS-179)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.4+11-LTS-179, mixed mode, sharing)

192.168.0.9 | CHANGED | rc=0 >>
java version "17.0.4" 2022-07-19 LTS
Java(TM) SE Runtime Environment (build 17.0.4+11-LTS-179)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.4+11-LTS-179, mixed mode, sharing)
```



