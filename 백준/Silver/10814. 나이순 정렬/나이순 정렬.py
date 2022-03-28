#10814
n=int(input())
templist=[]

for i in range(n):
    a,b=map(str,input().split())
    a=int(a)
    templist.append([a,b])
templist.sort(key=lambda x: x[0])

for i in templist:
    print(i[0],i[1])