from itertools import combinations_with_replacement
from collections import Counter
def delta_score(peach,lion):
    score = 0
    for i in range(11):
        p = peach.get(i,0)
        l = lion.get(i,0)
        if p==0 and l==0:
            continue
        elif l>p:
            score+=i
        else:
            score -=i
    return score

def solution(n, info):
    #peach = dict([10-i,cnt] for i,cnt in enumerate(info))
    peach = dict([(10-i,cnt) for (i,cnt) in enumerate(info) if cnt>0])
    max_delta = -float('inf')
    max_comb = None
    
    for comb in combinations_with_replacement([x for x in range(11)],n):
        lion = Counter(comb)
        delta = delta_score(peach,lion)
        if max_delta<delta:
            max_delta = delta
            max_comb = lion
        elif max_delta == delta:
            for i in range(11):
                if lion.get(i,0) < max_comb.get(i,0):
                    max_comb = max_comb
                    break
                    
    if max_delta <=0:
        return [-1]
    
    
    answer = [0]*11
    
    for i in range(11):
        answer[i]=max_comb.get(10-i,0)
        
        
    return answer