#!/usr/bin bash

npm vuepress-vite build docs

#推送到发布仓库
cd ./dist
git init
git add -A
git commit -m 'deploy 2.1.0'
git branch -M main
git remote add github-io-origin https://github.com/note-jf/note-jf.github.io.git
git push -f github-io-origin main

# 推送到编辑仓库
cd ../notemd
git add -A
git commit -m '更新'
git branch -M main
# 推送到 github 编辑仓库
git remote add github-origin https://github.com/note-jf/note-jf.git
git push -f github-origin main
# 推送到 gitee 编辑仓库
git remote add gitee-origin https://gitee.com/cps007/notemd.git
git push -f gitee-origin main
