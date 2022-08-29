import math
from collections import deque
def solution(people, limit):
    answer = 0
    not_escape = deque(sorted(people))
    while not_escape:
        temp = not_escape.pop()
        answer+=1
        if not_escape:
            if not_escape[0]+temp<=limit:
                not_escape.popleft()
        
        if not_escape:
            if temp<=limit//2:
                answer+=math.ceil(len(not_escape)/2)
                break
            
    return answer
