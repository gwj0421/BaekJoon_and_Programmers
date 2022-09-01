def func(n):
    _past1,_past2,_curr = 1,3,11
    if n ==1:
        return _past1
    elif n == 2:
        return _past2
    else:
        for _ in range(n-2):
            _curr = 4*_past2-_past1
            _past1, _past2 = _past2,_curr
        return _curr

def solution(n):
    answer = 0
    return func(n//2+1)%1000000007

solution(8)