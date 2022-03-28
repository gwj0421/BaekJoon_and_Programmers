#11651
import sys
templist=[]
for i in range(int(sys.stdin.readline())):
    templist.append(list(map(int,sys.stdin.readline().split())))
templist.sort(key=lambda x:(x[1],x[0]))
for i in templist:
    print(i[0],i[1])