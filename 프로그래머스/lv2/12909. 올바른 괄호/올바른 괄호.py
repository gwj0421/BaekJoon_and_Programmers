import collections

def solution(s):
    answer = True
    q = collections.deque()
    
    for i in s:
        if i=='(':
            q.append('(')
        elif i==')':
            if len(q)>0:
                q.pop()
            else:
                return False
    if q:
        return False
    else:
        return True