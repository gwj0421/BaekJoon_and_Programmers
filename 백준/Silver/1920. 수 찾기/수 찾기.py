#1920
testnum=int(input())
testlist=list(map(int,input().split()))
testlist.sort()

resultnum=int(input())
resultlist=list(map(int,input().split()))

answer=[0 for i in range(resultnum)]


for i in range(resultnum):
    target=resultlist[i]
    low=0
    high=testnum-1
    while low<=high:
        mid=(low+high)//2
        if testlist[mid]==target:
            answer[i]=1
            break
        elif testlist[mid]<target:
            low=mid+1
        else:
            high=mid-1
for i in answer:
    print(i)
