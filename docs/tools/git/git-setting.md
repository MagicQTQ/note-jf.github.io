---
title: git配置
category: tools
date: 2020-01-01
tag:
- Git
---


## git配置

[gitee ssh配置页](https://gitee.com/profile/sshkeys)
[github ssh配置页](https://github.com/settings/keys)

```bash
sudo apt-get install git
git --version

git config --global user.name "jf"		，这里随便取
git config --global user.email "iskong9@163.com" ，这里是邮箱
ssh-keygen -t rsa -b 4096 -C "iskong9@163.com" 按3次回车，创建ssh免密

ssh-add ~/.ssh/id_rsa
eval `ssh-agent -s`

cat ~/.ssh/id_rsa.pub
把id_rsa.pub内容复制到：gitee --> 安全设置 --> SSH公钥 --> 添加公钥 --> 把id_rsa.pub内容粘贴进来。
ssh -T git@gitee.com 输入yes以后提交代码就不用输入账号密码
ssh -T git@github.com	输入yes以后提交代码就不用输入账号密码
```



## 强制推送

https://blog.csdn.net/qq_42476834/article/details/108263267?spm=1001.2014.3001.5501

```bash
git pull origin develop –allow-unrelated-histories
git push -u origin develop -f
```

idea下载

https://download.jetbrains.8686c.com/idea/ideaIU-2020.3.4.exe



Git 全局设置:

```bash
git config --global user.name "jf"
git config --global user.email "iskong9@163.com"
```

创建 git 仓库:

```bash
mkdir javacode
cd javacode
git init 
touch README.md
git add README.md
git commit -m "first commit"
git remote add origin https://gitee.com/kong19/javacode.git
git push -u origin "master"
```

已有仓库?

```bash
cd existing_git_repo
git remote add origin https://gitee.com/kong19/javacode.git
git push -u origin "master"
```
