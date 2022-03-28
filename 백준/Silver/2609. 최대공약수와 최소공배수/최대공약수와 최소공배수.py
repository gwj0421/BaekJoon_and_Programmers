#2609

a,b=map(int,input().split())
if b>a:
    temp=a
    a=b
    b=temp

temp1=a
temp2=b

while True:
    div=temp1%temp2
    if div==0:
        print(temp2)
        break
    else:
        temp1=temp2
        temp2=div
print(a*b//temp2)
