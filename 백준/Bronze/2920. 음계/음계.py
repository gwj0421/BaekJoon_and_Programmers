def fun(temp,change):
    for i in range(len(temp)-1):
        if temp[i+1]!=temp[i]+change:
            return False
    return True

alist=[i for i in input().split()]
alist=list(map(int,alist))

if alist[1]==alist[0]+1:
    if fun(alist,1):
        print('ascending')
    else: print('mixed')
elif alist[1]==alist[0]-1:
    if fun(alist,-1):
        print('descending')
    else: print('mixed')
else:
    print('mixed')
