import sys


def plus(a, b):
    return a + b


def minu(a, b):
    return a - b


def divi(a, b):
    if a < 0:
        return -((-a) // b)
    return a // b


def mult(a, b):
    return a * b


def dfs(depth, value, pl, mi, mu, di):
    global maxVal, minVal
    if depth == N - 1:
        maxVal = max(maxVal, value)
        minVal = min(minVal, value)
        return
    if pl:
        dfs(depth + 1, plus(value, aList[depth + 1]), pl - 1, mi, mu, di)
    if mi:
        dfs(depth + 1, minu(value, aList[depth + 1]), pl, mi - 1, mu, di)
    if mu:
        dfs(depth + 1, mult(value, aList[depth + 1]), pl, mi, mu - 1, di)
    if di:
        dfs(depth + 1, divi(value, aList[depth + 1]), pl, mi, mu, di - 1)


def solution():
    global N, aList, maxVal, minVal
    input = sys.stdin.readline
    N = int(input().rstrip())
    aList = list(map(int, input().split()))
    operations = list(map(int, input().split()))
    maxVal, minVal = -sys.maxsize, sys.maxsize
    dfs(0, aList[0], *operations)
    print(maxVal)
    print(minVal)


solution()