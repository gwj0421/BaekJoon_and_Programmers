from collections import deque 

def check(brackets):
    b1 = []
    b2 = []
    b3 = []
    last = []
    for i in brackets:
        if i=='(':
            b1.append('(')
            last.append('(')
        elif i=='[':
            b2.append('[')
            last.append('[')
        elif i=='{':
            b3.append('{')
            last.append('{')
        elif i==')':
            if b1 and ''.join(last[-1:])=='(':
                last.pop()
                b1.pop()
            else:
                return False
        elif i==']':
            if b2 and ''.join(last[-1:])=='[':
                last.pop()
                b2.pop()
            else:
                return False
        elif i=='}':
            if b3 and ''.join(last[-1:])=='{':
                last.pop()
                b3.pop()
            else:
                return False
    
    if b1 or b2 or b3:
        return False
    return True
            

def solution(s):
    s = deque(s)
    answer = 0
    for _ in range(len(s)):
        if check(s):
            answer+=1
        s.rotate(-1)
    return answer