#7568
inp=int(input())
templist=[]
temp=[1 for i in range(inp)]
for i in range(inp):
    templist.append(list(map(int,input().split())))

for i in range(inp):
    for j in range(inp):
        if i!=j and templist[i][0]<templist[j][0] and templist[i][1]<templist[j][1]:
            temp[i]+=1

for i in temp:
    print(i,end=" ")