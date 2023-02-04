#10816
from collections import Counter
n=int(input())
nlist=Counter(list(map(int,input().split())))
m=int(input())
mlist=list(map(int,input().split()))
result=[]
for i in range(m):
    temp=nlist.get(mlist[i])
    if temp==None:
        temp=0
    result.append(temp)
print(*result,sep=' ')