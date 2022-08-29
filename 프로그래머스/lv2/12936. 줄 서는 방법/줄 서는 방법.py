def func(num):
    if num<1:
        return 1
    else:
        return num * func(num-1)

def solution(n,k):
    answer = []
    temp = [x for x in range(1,n+1)]
    k-=1
    while k>=0 and temp:
        stand = func(len(temp)-1)
        a1,a2 = divmod(k,stand)
        
        answer.append(temp.pop(a1))
        k = a2
        
    return answer