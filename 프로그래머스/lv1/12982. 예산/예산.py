def budget_allocation(blist,b):
    blist.sort()
    cnt=0
    for i in blist:
        b=b-i
        if b>=0:
            cnt+=1
        else :
            break
    return cnt

def solution(d, budget):
    return budget_allocation(d,budget)