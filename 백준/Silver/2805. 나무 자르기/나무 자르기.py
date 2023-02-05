n,m=map(int,input().split())
treelen=list(map(int,input().split()))
remaintree=[0 for i in range(n)]
low=1
high=max(treelen)

while low<=high:
    mid=(low+high)//2
    treeleft=0
    for i in range(n):
        if mid<treelen[i]:
            treeleft+=treelen[i]-mid
    if treeleft>=m:
        low=mid+1
    else:
        high=mid-1
print(high)