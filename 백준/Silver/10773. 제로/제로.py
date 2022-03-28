#10773
k=int(input())
temp=[]
result=[]
for i in range(k):
    temp.append(int(input()))
for i in range(k):
    if temp[i]==0:
        result.pop(-1)
    else :
        result.append(temp[i])

print(sum(result))