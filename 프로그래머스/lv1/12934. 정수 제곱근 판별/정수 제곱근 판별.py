def solution(n):
    temp=n**0.5
    if temp.is_integer():
        return (temp+1)**2
    else:
        return -1