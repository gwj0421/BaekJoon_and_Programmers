#1966
num=int(input())
for i in range(num):
    n,m=map(int,input().split())
    temp=list(map(int,input().split()))
    indexlist=list(0 for i in range(n))
    indexlist[m]=1
    order=0
    while True:
        if temp[0]==max(temp):
            order+=1
            if indexlist[0]==1:
                print(order)
                break
            else :
                temp.pop(0)
                indexlist.pop(0)
        else :
            temp.append(temp[0])
            indexlist.append(indexlist[0])
            del temp[0]
            del indexlist[0]