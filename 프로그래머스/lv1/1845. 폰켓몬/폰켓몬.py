def solution(nums):
    answer = 0
    temp=set(nums)
    if len(temp)<len(nums)//2:
        answer=len(temp)
    else:
        answer=len(nums)//2
    return answer

# def solution(ls):
#     return min(len(ls)/2, len(set(ls)))