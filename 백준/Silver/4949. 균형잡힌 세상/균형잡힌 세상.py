def judge(templist):
    line=[]
    for i in templist:
        if i=='(' or i=='[':
            line.append(i)
        elif i==')':
            if line and line[-1]=='(':
                line.pop()
            else :
                return False
        elif i==']':
            if line and line[-1]=='[':
                line.pop()
            else :
                return False   
    if line:
        return False             
    return True

while True:
    inp=input()
    englist=list(inp)
    if inp=='.':
        break
    if judge(englist):
        print('yes')
    else :
        print('no')
    