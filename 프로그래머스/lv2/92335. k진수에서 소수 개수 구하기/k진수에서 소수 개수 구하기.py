from collections import deque
def decimal_converter(number,k):
    _output = deque()
    while number>0:
        value,remain = divmod(number,k)
        _output.appendleft(remain)
        number = value
    return "".join(map(str,_output))

def judge(number):
    if number ==1:
        return False
    for i in range(2,int(number**0.5)+1):
        if number%i == 0:
            return False
    return True

def solution(n, k):
    answer = 0
    temp = decimal_converter(n,k).split('0')
    temp = [int(x) for x in decimal_converter(n,k).split('0') if x != '']
    print(temp)
    max_value = max(temp)
    for i in temp:
        if judge(i):
            answer+=1
    return answer