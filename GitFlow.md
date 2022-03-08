## Git Flow

Make sure you are at the outermost directory and on the branch main

1\. Add all git files that you want to commit

Add all files in current directory ( the . stands for current)

```terminal
git add .
```

Add all files in a specific directory

```terminal
git add <path>
```

2\. Commit the files with a message

```terminal
git commit -m <message>
```

For Example:

```terminal
git commit -m "cleaned dataset"
```

3\. Change to another branch other than main

```terminal
git checkout -b <branch_name>
```

For Example:

```terminal
git checkout -b clean_dataset
```

4\. Pull latest updates from github

```terminal
git pull origin <branch_name>
```

<strong>Make sure there is no merge conflict before you push the code</strong>

5\. Push to branch

```terminal
git push origin <branch_name>
```

6\. Merge into main branch at github

- Manually merge in github

7\. Go back to main branch

```terminal
git checkout main
```

8\. Delete branch created

- Manually delete branch
