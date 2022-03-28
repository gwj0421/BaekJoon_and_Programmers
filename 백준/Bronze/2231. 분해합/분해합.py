#2231
import math
def digit_length(n):
    return int(math.log10(n))+1 if n else 0

temp=1
inp=int(input())
while temp<inp:
    cur=temp
    sum=0
    while cur!=0:
        sum += cur%10
        cur=cur//10
    if temp+sum==inp:
        print(temp)
        break
    else :
        temp+=1
if temp==inp:
    print(0)
    