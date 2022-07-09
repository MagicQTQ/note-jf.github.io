#!/usr/bin bash
# 确保脚本抛出遇到的错误
set -e
# 生成静态文件
npm vuepress-vite build docs
# 进入生成的文件夹
cd ../dist/
git init && git add -A && git commit -m 'deploy' && git branch -M main \
&& git remote add origin https://github.com/topsjf/topsjf.github.io.git && git push -f origin main
# 如果发布到 https://<USERNAME>.github.io
# 如果发布到 https://<USERNAME>.github.io/<REPO>
