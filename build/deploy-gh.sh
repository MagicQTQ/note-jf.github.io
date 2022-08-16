

# pages

cd ./dist
git init
git add -A
git branch -M pages
git commit -m 'deploy 2.2.0'
git remote add github-io-origin https://github.com/note-jf/note-jf.github.io.git
git push -f github-io-origin pages
git push -f git@github.com:note-jf/note-jf.github.io.git main:pages

cd -

# main
git commit -m 'deploy 2.2.0'
git remote add github-io-origin https://github.com/note-jf/note-jf.github.io.git
git push -f github-io-origin main

