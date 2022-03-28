#1654
num,div=map(int,input().split())
alist=[]

for i in range(num):
    alist.append(int(input()))

low=1
high=max(alist)

while low<=high:
    mid=(high+low)//2
    count=0
    for i in alist:
        count+=i//mid
    if count >=div:
        low=mid+1
    else:
        high=mid-1
print(high)



