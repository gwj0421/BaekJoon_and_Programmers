import sys


def josephus(n, k):
    ori = [str(i) for i in range(1, n + 1)]
    ans = []
    idx = 0
    while ori:
        idx = (idx + k - 1) % len(ori)
        ans.append(ori.pop(idx))
    print('<' + ', '.join(ans) + '>')


def solution():
    input = sys.stdin.readline
    N, K = map(int, input().split())
    josephus(N, K)


solution()