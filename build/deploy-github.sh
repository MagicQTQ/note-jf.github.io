#!/usr/bin bash

npm vuepress-vite build docs

#推送到发布仓库
cd ./dist
git init
git add -A
git commit -m 'deploy 2.1.2 更新 ansible-docker and jdk'
git branch -M main
git remote add github-io-origin https://github.com/note-jf/note-jf.github.io.git
git push -f github-io-origin main
