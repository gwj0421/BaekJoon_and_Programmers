import sys


def cal(a, b, c):
    if b == 1:
        return a % c
    else:
        x = cal(a, b // 2, c) % c
        if b % 2 == 0:
            return x * x % c
        else:
            return x * x * a % c


def solution():
    input = sys.stdin.readline
    a, b, c = map(int, input().split())
    print(cal(a, b, c))


solution()