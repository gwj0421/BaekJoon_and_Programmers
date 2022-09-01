def cal_function(A,B):
    _m = len(A)
    _n = len(B[0])
    table = [[0 for _ in range(_n)] for _ in range(_m)]
    B = [x for x in zip(*B)]
    for m in range(_m):
        for n in range(_n):
            table[m][n] = sum([x*y for x,y in zip(A[m],B[n])])
    return table

def solution(arr1, arr2):
    answer = [[]]  
    table = cal_function(arr1,arr2)
    
    return table