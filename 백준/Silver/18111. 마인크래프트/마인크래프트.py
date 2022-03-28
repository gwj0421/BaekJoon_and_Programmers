#18111
n,m,b=map(int,input().split())
temp=[0 for i in range(n)]
for i in range(n):
    temp[i]=list(map(int,input().split()))
ans=1000000000000
for i in range(257):
    max=0
    min=0
    for j in range(n):
        for k in range(m):
            if temp[j][k]<i:
                min+=i-temp[j][k]
            else :
                max+=temp[j][k]-i
    remain=b+max
    if min>remain:
        continue
    time=2*max+min
    if time<=ans:
        ans=time
        height=i
print(ans,height)
