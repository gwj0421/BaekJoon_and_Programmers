n,m=map(int,input().split())
original=[]
count=[]

for _ in range(n):
    original.append(input())
    
for i in range(n-7):
    for j in range(m-7):
        index0=0
        index1=0
        for a in range(i,i+8):
            for b in range(j,j+8):
                if (a+b)%2==0:
                    if original[a][b]=='W':
                        index0+=1
                    else :
                        index1+=1
                else :
                    if original[a][b]=='B':
                        index0+=1
                    else :
                        index1+=1
        count.append(min(index0,index1))
print(min(count))

