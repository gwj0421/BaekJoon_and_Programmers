import sys
import heapq


def dijkstra(start, end):
    INF = sys.maxsize
    costTable = [INF for _ in range(N + 1)]
    costTable[start] = 0

    heap = []
    heapq.heappush(heap, (0, start))

    while heap:
        nowCost, nowPos = heapq.heappop(heap)
        if costTable[nowPos] < nowCost:
            continue
        for nextPos, nextCost in network[nowPos]:
            stand = nowCost + nextCost
            if stand < costTable[nextPos]:
                costTable[nextPos] = stand
                heapq.heappush(heap, (stand, nextPos))

    print(costTable[end])


def solution():
    global network, N
    input = sys.stdin.readline
    N = int(input())
    M = int(input())
    network = {x: [] for x in range(1, N + 1)}

    for _ in range(M):
        s, e, cost = map(int, input().split())
        network[s].append((e, cost))
    start, end = map(int, input().split())
    dijkstra(start, end)


solution()
