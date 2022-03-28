#1874
num=int(input())
answer=[]
stack=[]
cur=1
flag=True
for i in range(num):
    num=int(input())
    while cur<=num:
        stack.append(cur)
        answer.append('+')
        cur=cur+1
    if stack[-1]==num:
        answer.append('-')
        stack.pop()
    else:
        flag=False
if flag:
    for i in answer:
        print(i)
else :
    print('NO')



