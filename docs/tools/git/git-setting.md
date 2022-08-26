---
icon: edit
title: git配置
category: Tools
date: 2020-01-01
tag:
- Git
---


## git配置

- [gitee ssh配置页](https://gitee.com/profile/sshkeys)
- [github ssh配置页](https://github.com/settings/keys)

**安装Git**

```bash
sudo apt-get install git
git --version
```

**Git 全局设置**

```bash
git config --global user.name "jf"		# 这里随便取
git config --global user.email "iskong9@163.com"   # 这里是邮箱
```

**创建公钥-私钥**

```bash
ssh-keygen -t rsa -b 4096 -C "iskong9@163.com"    # 按3次回车，创建ssh免密

# 添加私钥
ssh-add ~/.ssh/id_rsa   
eval `ssh-agent -s`
```

**查看公钥**

```bash
cat ~/.ssh/id_rsa.pub

PS C:\Users\k> cat ~/.ssh/id_rsa.pub
ssh-rsa AAAAB3Nza...省略...azntfclnRASQ== iskong9@163.com
```

**查看私钥**

```bash
PS C:\Users\k> cat ~/.ssh/id_rsa
-----BEGIN OPENSSH PRIVATE KEY-----
b3BlbnNzaC1rZXktdjEAAAAABG5vbBAAACFwA
...省略...
8vANsqYhNgAfAAAAD2lza29uZzlAMTYzLmNvbQECAw==
-----END OPENSSH PRIVATE KEY-----
```

把 id_rsa.pub 内容复制到：gitee --> 安全设置 --> SSH公钥 --> 添加公钥 --> 把id_rsa.pub内容粘贴进来。

**校验公钥应用是否生效**

```bash
ssh -T git@gitee.com    # 输入yes以后提交代码就不用输入账号密码
ssh -T git@github.com	# 输入yes以后提交代码就不用输入账号密码

PS C:\Users\k> ssh -T git@gitee.com
Hi jin! You've successfully authenticated, but GITEE.COM does not provide shell access.'
PS C:\Users\k> ssh -T git@github.com
Hi top! You've successfully authenticated, but GitHub does not provide shell access.'
```


## 强制推送

[解决 Push rejected: Push to xxxx/master was rejected](https://blog.csdn.net/qq_42476834/article/details/108263267)

```bash
git pull origin develop –allow-unrelated-histories
git push -u origin develop -f
```

## Gitee 推送

创建 git 仓库:

```bash
mkdir xxx
cd xxx
git init 
touch README.md
git add -A
git commit -m "first commit"
git remote add origin https://gitee.com/xxx/xxx.git
git push -u origin "master"
```

已有仓库

```bash
cd xxx
git remote add origin https://gitee.com/xxx/xxx.git
git push -u origin "master"
```

## GitHub 推送

```yaml
git init
git add -A
git branch -M main
git commit -m '第3次提交并解决了xxx-BUG'
# HTTPS 模式
git remote add github-io-origin https://github.com/note-jf/note-jf.github.io.git
git push -f github-io-origin main

# SSH 模式
git push -f git@github.com:note-jf/note-jf.github.io.git main:pages
```

