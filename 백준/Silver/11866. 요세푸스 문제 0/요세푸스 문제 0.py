#11866
from collections import deque
n,k=map(int,input().split())
line=[i+1 for i in range(n)]
result=[]
cur=k-1
while True:
    if line:
        if cur>len(line)-1:
            cur%=len(line)
    else : 
        break

    result.append(line.pop(cur))
    cur+=k-1
print('<',end='')
for i in range(n-1):
    print(result[i],end=', ')
print(result[-1],'>',sep='')