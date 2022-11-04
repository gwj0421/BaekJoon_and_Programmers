# def solution(numbers):
#     numbers = list(map(str, numbers))
#     numbers.sort(key=lambda x: x*3, reverse=True)
#     return str(int(''.join(numbers)))
import functools
def cal(a,b):
    a1 = int(a+b)
    a2 = int(b+a)
    if a1>a2 :
        return 1
    elif a1==a2:
        return 0
    else:
        return -1

def solution(numbers):
    temp = list(map(str,numbers))
    temp.sort(reverse=True,key=functools.cmp_to_key(cal))
    return str(int(''.join(temp)))