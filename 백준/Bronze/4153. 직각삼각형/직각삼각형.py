import sys
def fun(int1,int2,int3):
    alist=[int1,int2,int3]
    temp=max(int1,int2,int3)
    alist.remove(temp)
    if temp**2==alist[0]**2+alist[1]**2:
        return 'right'
    return 'wrong'
while True:
    a,b,c = map(int,input().split())
    if a==0 and b==0 and c==0:
        break
    else:
        print(fun(a,b,c))