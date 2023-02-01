import sys
import heapq


def dikstra(start):
    heap = []
    distTable = [INF for _ in range(V + 1)]
    distTable[start] = 0
    heapq.heappush(heap, (0, start))
    while heap:
        nowDist, nowPos = heapq.heappop(heap)
        if distTable[nowPos] < nowDist:
            continue
        for nextPos, nextDist in graph[nowPos]:
            stand = nowDist + nextDist
            if distTable[nextPos] > stand:
                distTable[nextPos] = stand
                heapq.heappush(heap, (stand, nextPos))
    return distTable


def solution():
    global INF, V, graph
    input = sys.stdin.readline
    V, e = map(int, input().split())
    k = int(input().rstrip('\n'))
    graph = {x: [] for x in range(1, V + 1)}
    INF = sys.maxsize

    for _ in range(e):
        u, v, w = map(int, input().split())
        graph[u].append([v, w])

    result = dikstra(k)
    for i in range(1, V + 1):
        if result[i] == INF:
            print('INF')
        else:
            print(result[i])


solution()