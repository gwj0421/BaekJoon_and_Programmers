import sys


def cal(x, p):
    result = 0
    while x > 0:
        temp = x % 10
        x = x // 10
        result += temp ** p
    return result


def solution():
    input = sys.stdin.readline
    a, p = map(int, input().split())

    nowNum = a
    idx = 0
    numbers = {nowNum: idx}

    while True:
        nextNum = cal(nowNum, p)
        if nextNum in numbers:
            ans = numbers[nextNum]
            break

        idx += 1
        numbers[nextNum] = idx
        nowNum = nextNum
    print(ans)


solution()