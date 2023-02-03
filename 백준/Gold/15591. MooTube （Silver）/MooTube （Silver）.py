import sys
from collections import deque


def bfs(start, k):
    visited = [-1 for _ in range(n + 1)]
    visited[start] = sys.maxsize
    needVisited = deque([start])
    cnt = 0
    while needVisited:
        nowPos = needVisited.popleft()
        for nextPos, nextUSADO in graph[nowPos]:
            if visited[nextPos] < 0:
                visited[nextPos] = min(visited[nowPos], nextUSADO)
                if visited[nextPos] >= k:
                    cnt += 1
                needVisited.append(nextPos)
    return cnt


def solution():
    global n, graph
    input = sys.stdin.readline
    n, q = map(int, input().split())
    graph = {x: [] for x in range(1, n + 1)}
    for _ in range(n - 1):
        pi, qi, ri = map(int, input().split())
        graph[pi].append([qi, ri])
        graph[qi].append([pi, ri])

    for _ in range(q):
        ki, vi = map(int, input().split())
        print(bfs(vi, ki))


solution()