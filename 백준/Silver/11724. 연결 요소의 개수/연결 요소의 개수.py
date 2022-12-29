from collections import deque
import sys
input = sys.stdin.readline

def bfsController(n, edges):
    visited = deque([False for _ in range(n + 1)])
    connectedCnt = 0
    for idx in range(1, n + 1):
        if not visited[idx]:
            connectedCnt += 1
            bfs(edges,idx,visited)
    return connectedCnt

def bfs(edges,idx,visited):
    visited[idx] = True
    needVisited = deque([idx])
    while needVisited:
        now = needVisited.popleft()
        for i in edges[now]:
            if not visited[i]:
                visited[i] = True
                needVisited.append(i)



def solution():
    n, m = map(int, input().split())
    edges = {x: [] for x in range(1, n + 1)}
    for _ in range(m):
        start, end = map(int, input().split())
        edges[start].append(end)
        edges[end].append(start)

    print(bfsController(n, edges))


solution()