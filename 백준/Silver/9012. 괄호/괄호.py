#9012
inp=int(input())
for i in range(inp):
    line=input()
    temp=[]
    flag=True
    for j in line:
        if j=='(':
            temp.append(j)
        elif j==')':
            if not temp:
                temp.append(j)
            if temp[-1]=='(':
                temp.pop()

                
    if not temp:
        print('YES')
    else :
        print('NO')
