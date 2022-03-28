n=list(map(int,input()))
flag=False

for i in range(1,len(n)):
    leftsum=1
    rightsum=1
    
    left=n[0:i]
    right=n[i:]
    for j in left:
        leftsum*=j
    for j in right:
        rightsum*=j
    if leftsum==rightsum:
        flag=True
        
if flag:
    print("YES")
else :
    print("NO")