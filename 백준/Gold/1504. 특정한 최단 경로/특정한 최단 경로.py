import sys
import heapq


def dijkstra(start):
    distTable = [sys.maxsize for _ in range(n + 1)]
    distTable[start] = 0
    heap = []
    heapq.heappush(heap, (0, start))
    while heap:
        nowTime, nowPosition = heapq.heappop(heap)
        if distTable[nowPosition] < nowTime:
            continue
        for nextPosition, nextTime in graph[nowPosition]:
            stand = nowTime + nextTime
            if distTable[nextPosition] > stand:
                distTable[nextPosition] = stand
                heapq.heappush(heap, (stand, nextPosition))
    return distTable


def solution():
    global graph, n
    input = sys.stdin.readline
    n, e = map(int, input().split())
    graph = {x: [] for x in range(1, n + 1)}
    for _ in range(e):
        a, b, c = map(int, input().split())
        graph[a].append([b, c])
        graph[b].append([a, c])
    node1, node2 = map(int, input().split())
    startStand = dijkstra(1)
    endStand = dijkstra(n)

    left1 = startStand[node1]
    left2 = startStand[node2]
    mid = dijkstra(node1)[node2]
    right1 = endStand[node1]
    right2 = endStand[node2]

    if (
            (left1 == sys.maxsize and left2 == sys.maxsize)
            or (right1 == sys.maxsize and right2 == sys.maxsize)
            or (mid == sys.maxsize)

    ):
        print(-1)
        return
    print(min(left1 + mid + right2,
              left2 + mid + right1,
              left1 + mid * 2 + right1,
              left2 + mid * 2 + right2
              ))


solution()