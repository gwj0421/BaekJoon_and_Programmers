def fac(number,end):
    if(number>end):
        return number*fac(number-1,end)
    else:
        return 1
for i in range(int(input())):
    b,a=map(int,input().split())
    print(fac(a,a-b)//fac(b,1))