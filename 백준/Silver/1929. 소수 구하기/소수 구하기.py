m,n=map(int,input().split())
n+=1
line=[True]*(n)
end=int(n**0.5)
for i in range(2,end+1):
    if line[i]:
        for j in range(i+i,n,i):
            line[j]=False

for i in range(m,n):
    if i>1 and line[i]==True:
        print(i)
