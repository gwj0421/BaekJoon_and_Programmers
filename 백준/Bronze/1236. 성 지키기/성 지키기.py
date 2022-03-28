n,m=map(int,input().split())
castle=[]
for i in range(n):
    inp=input()
    castle.append(list(inp))
    
cntr=0
cntc=0
for i in range(n):
    if 'X' not in castle[i]:
        cntr+=1
for i in range(m):
    if 'X' not in list(castle[j][i] for j in range(n)):
        cntc+=1
print(max(cntr,cntc))