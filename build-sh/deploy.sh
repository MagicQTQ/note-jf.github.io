#!/usr/bin bash
# 确保脚本抛出遇到的错误
set -e
# 生成静态文件
npm vuepress-vite build docs
# 进入生成的文件夹
cd ../dist/
# 如果是发布到自定义域名
# echo 'www.example.com' > CNAME
git init
git add -A
git commit -m 'deploy'
git branch -M main
git remote add origin https://github.com/topsjf/topsjf.github.io.git
git push -f origin main
# 如果发布到 https://<USERNAME>.github.io
# 如果发布到 https://<USERNAME>.github.io/<REPO>
#git push -f git@github.com:topsjf/topsjf.github.io.git main:github-pages
