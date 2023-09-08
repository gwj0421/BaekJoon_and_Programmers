import sys
from collections import deque


# 0  1   2  3  4  5  6  7  8  9
# 10 20 21 30 31 32 40 41 42 43 ~ 98
# 321 410

def bfs(N):
    needVistied = deque([i for i in range(1, 10)])
    ans = [-1] + [i for i in range(10)]
    if N < 11:
        print(ans[N])
        return
    elif N > 1023:
        print(-1)
        return
    else:
        while needVistied:
            node = needVistied.popleft()
            for num in range(10):
                if node % 10 > num:
                    ans.append(node * 10 + num)
                    if (node * 10 + num) % 10 > 0:
                        needVistied.append(node * 10 + num)
        print(ans[N])


def solution():
    input = sys.stdin.readline
    N = int(input().rstrip())
    bfs(N)


solution()