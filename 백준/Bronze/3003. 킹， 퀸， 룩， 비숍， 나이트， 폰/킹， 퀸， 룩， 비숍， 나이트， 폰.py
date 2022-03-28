alist=list(map(int,[j for j in input().split()]))
model=[1,1,2,2,2,8]
for i in range(len(alist)):
    alist[i]=model[i]-alist[i]
    print(alist[i],end=' ')