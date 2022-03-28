#11650
templist=[]
for i in range(int(input())):
    templist.append(list(map(int,input().split())))
templist.sort()
for i in templist:
    print(i[0],i[1])
