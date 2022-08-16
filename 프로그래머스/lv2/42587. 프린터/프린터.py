def solution(priorities, location):
    answer = 0
    temp = [x for x in range(len(priorities))]
    stack = []
    while True:
        now_val = priorities.pop(0)
        now_val2 = temp.pop(0)
        
        if not priorities:
            stack.append(now_val2)
            break
            
        if now_val < max(priorities):
            priorities.append(now_val)
            temp.append(now_val2)
        else:
            stack.append(now_val2)
        
    return stack.index(location)+1
