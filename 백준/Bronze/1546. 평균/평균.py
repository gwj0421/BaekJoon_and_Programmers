#@title 백준 1546
number=int(input())
data=list(map(int,input().split()))
sum=0
max=data[0]
for i in data:
    if i>max:
        max=i
for i in data:
    sum+=i/max*100
print(sum/number)



    


        