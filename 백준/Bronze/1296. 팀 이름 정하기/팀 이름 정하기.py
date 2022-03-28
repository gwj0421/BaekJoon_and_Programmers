def LoveCal(heros,girlslist):
    alist=list(girlslist)
    blist=list(heros)
    L=alist.count('L')+blist.count('L')
    O=alist.count('O')+blist.count('O')
    V=alist.count('V')+blist.count('V')
    E=alist.count('E')+blist.count('E')
    return ((L+O)*(L+V)*(L+E)*(O+V)*(O+E)*(V+E)) % 100

minsik=input()
girlsNum=int(input())
girls=[]
for i in range(girlsNum):
    girls.append(input())
girls.sort()
max_cal=-1
id=0
for i in range(girlsNum):
    temp1=LoveCal(minsik,girls[i])
    if temp1>max_cal:
        max_cal=temp1
        id=i

print(girls[id])