#!/usr/bin bash

npm vuepress-vite build docs

cd ./dist
git init
git add -A
git commit -m 'deploy 2.1.0'
git branch -M main
git remote add origin https://github.com/topsjf/topsjf.github.io.git
git push -f origin main
# 如果发布到 https://<USERNAME>.github.io
# 如果发布到 https://<USERNAME>.github.io/<REPO>
