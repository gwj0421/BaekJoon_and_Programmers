import math
def solution(n,a,b):
    now_stage = [x for x in range(1,n+1)]
    max_match = int(math.log(n, 2))
        
    for i in range(1,max_match+1):
        new_stage = []
        while now_stage:
            left = now_stage.pop()
            right = now_stage.pop()
            if (left == a and right == b) or (right == a and left == b):
                return i
            elif left == a or right == a:
                new_stage.append(a)
            elif left == b or right == b:
                new_stage.append(b)
            else:
                new_stage.append(left)
        now_stage = new_stage

    return -1