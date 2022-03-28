import sys

def fun(str):
    for i in range(len(str)//2):
        if str[i]!=str[len(str)-1-i]:
            return 'no'
    return 'yes'
while True:
    temp=sys.stdin.readline().strip()
    if temp=='0':
        break
    print(fun(temp))
