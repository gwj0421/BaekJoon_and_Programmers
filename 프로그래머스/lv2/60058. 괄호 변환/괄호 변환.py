def func_2(w):
    left,right=0,0
    for i in range(len(w)):
        if w[i]=='(':
            left+=1
        else:
            right+=1
        if left==right:
            u=w[:i+1]
            v=w[i+1:]
            return u,v

def isRight(temp):
    stack=[]
    stack.append(temp[0])
    for i in temp[1:]:
        if stack[-1:]==['('] and i==')':
            stack.pop()
        else:
            stack.append(i)
    if stack:
        return False
    else:
        return True
    
def solution(p):
    # func_1
    if not p:
        return ''
    
    # func_2
    u,v=func_2(p)
    
    # func_3
    if isRight(u):
        # func_3-1
        return u+solution(v)
    else:
        # func_4-1
        answer="("
        # func_4-2
        answer+=solution(v)
        # func_4-3
        answer+=")"
        # func_4-4
        for i in u[1:-1]:
            if i=="(":
                answer+=")"
            else:
                answer+="("
        # func_4-5
        return answer