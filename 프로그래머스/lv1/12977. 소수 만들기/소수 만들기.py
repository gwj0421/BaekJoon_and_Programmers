import itertools
import math

def solution(nums):
    answer = 0
    combination=list(itertools.combinations(nums,3))
    for i in combination:
        s = sum(i)
        for num in range(2,int(math.sqrt(s))+1):
            if s % num == 0:
                answer+=1
                break
                
    return len(combination)-answer