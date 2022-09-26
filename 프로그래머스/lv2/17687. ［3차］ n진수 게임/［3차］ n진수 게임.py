from collections import deque

def decimal_conversion(number,decimal):
    _output = deque()
    over_dict = {10:'A',11:'B',12:'C',13:'D',14:'E',15:'F'}
    while True:
        value,remain = divmod(number,decimal)
        number = value
        if remain > 9:
            _output.appendleft(over_dict[remain])
        else:
            _output.appendleft(remain)
        if number == 0:
            break
    return _output

def solution(n, t, m, p):
    answer = ''
    cnt = 0
    num = -1
    while True:
        num +=1
        for j in decimal_conversion(num,n):
            cnt+=1
            if (cnt-p) % m == 0:
                answer+=str(j)
            if len(answer) == t:
                return answer
    return 0