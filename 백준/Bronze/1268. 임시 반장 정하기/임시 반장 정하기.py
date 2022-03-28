#version2
num=int(input())
temp=[list(map(int, input().split())) for _ in range(num)]
friendlist=[[0] * num for _ in range(num)]
for i in range(5):
    collist=[tt[i] for tt in temp]
    for j in range(len(collist)):
        if collist.count(collist[j])>1:
            for k in range(len(collist)):
                if collist[j]==collist[k] and j!=k:
                    friendlist[j][k]=1

li=[sum(student) for student in friendlist]
max_value=max(li)
print(li.index(max_value)+1)
                
                
                