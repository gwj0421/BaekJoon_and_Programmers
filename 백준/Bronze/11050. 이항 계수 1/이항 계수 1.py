def fun(n,k):
    if n>k:
        return n*fun(n-1,k)
    else :
        return 1
a,b=map(int,input().split())
print(fun(a,b)//fun(a-b,1))