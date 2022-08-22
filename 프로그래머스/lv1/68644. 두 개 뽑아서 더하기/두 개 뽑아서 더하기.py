import itertools

def indexCombination (num):
    num=list(itertools.combinations(num, 2))
    for i,j in enumerate(num):
        num[i]=sum(j)
        
    return sorted(set(num))

def solution(numbers):
    return indexCombination(numbers)