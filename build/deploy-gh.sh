#!/usr/bin/env bash

npm run docs:build

cd ./dist
git init
git add -A
git commit -m 'deploy 2.2.0'
git push -f git@github.com:note-jf/note-jf.github.io.git main:main

cd -

git branch -M main
git remote add github-io-origin https://github.com/note-jf/note-jf.github.io.git
git push -f github-io-origin main

