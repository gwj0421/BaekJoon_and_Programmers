import collections
def solution(queue1, queue2):
    q1 = collections.deque(queue1)
    q2 = collections.deque(queue2)
    target,flag =divmod(sum(q1+q2),2)
    cnt = 0
    
    if flag == 1:
        return -1
    else:
        temp_sum = sum(q1)
        while q1 and q2:
            if temp_sum>target:
                cnt+=1
                minus = q1.popleft()
                temp_sum-=minus
            elif temp_sum<target:
                cnt+=1
                plus = q2.popleft() 
                temp_sum+= plus
                q1.append(plus)
            else:
                return cnt
        return -1