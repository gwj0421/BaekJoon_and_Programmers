import sys


def splitRope(num):
    cnt = 0
    for rope in ropes:
        cnt += rope // num
    return cnt


def paramSearch(start, end, target):
    while start <= end:
        mid = (start + end) // 2
        if splitRope(mid) >= target:
            start = mid + 1
        else:
            end = mid - 1
    print(end)


def solution():
    global ropes
    input = sys.stdin.readline
    k, n = map(int, input().split())
    ropes = []
    for _ in range(k):
        ropes.append(int(input().rstrip('n')))
    paramSearch(0, sys.maxsize, n)


solution()