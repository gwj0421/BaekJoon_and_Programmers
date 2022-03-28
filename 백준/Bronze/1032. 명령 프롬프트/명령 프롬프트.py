temp=[]
num=int(input())
for i in range(num):
    temp.append(list(input()))
result=temp[0]
count=len(temp[0])
for i in range(1,num):
    for j in range(count):
        if result[j]!=temp[i][j]:
            result[j]='?'
for i in result:
    print(i,end='')


