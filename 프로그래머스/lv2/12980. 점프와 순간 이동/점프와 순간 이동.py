def solution(n):
    ans = 0
    while n !=0:
        value,remain = divmod(n,2)
        n = value
        if remain:
            ans+=1

    return ans