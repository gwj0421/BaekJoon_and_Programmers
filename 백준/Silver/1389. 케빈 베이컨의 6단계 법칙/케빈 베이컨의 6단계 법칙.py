from collections import deque


def bfs(start):
    visited = [-1 for _ in range(n)]
    visited[start - 1] = 0
    needVisited = deque([start])
    while needVisited:
        now = needVisited.popleft()
        for next in graph[now]:
            if visited[next - 1] < 0:
                visited[next - 1] = visited[now - 1] + 1
                needVisited.extend(graph[now])
    return sum(visited)


def solution():
    global n, m, graph
    n, m = map(int, input().split())
    graph = {x: [] for x in range(1, n + 1)}
    ans = []
    for _ in range(m):
        edgeA, edgeB = map(int, input().split())
        graph[edgeA].append(edgeB)
        graph[edgeB].append(edgeA)

    for startPoint in range(1, n + 1):
        ans.append([startPoint, bfs(startPoint)])
    ans.sort(reverse=True, key=lambda x: (x[1], x[0]))
    print(ans[-1][0])


solution()