"""
def solution(lottos, win_nums):
    ranking={6:1,5:2,4:3,3:4,2:5,1:6,0:6}
    zero_num=lottos.count(0)
    
    lottos=set(lottos)
    win_nums=set(win_nums)
    count=len(lottos&win_nums)
    
    answer=[ranking[count+zero_num],ranking[count]]
    return answer
"""

def solution(lottos, win_nums):
    ranking=[6,6,5,4,3,2,1]
    
    zero_num=lottos.count(0)
    sum=0
    for i in lottos:
        if i in win_nums:
            sum+=1
    answer=[ranking[zero_num+sum],ranking[sum]]
    return answer