import heapq
import sys


def dijkstra(start):
    timeTable = [sys.maxsize for _ in range(n + 1)]
    timeTable[start] = 0
    heap = []
    heapq.heappush(heap, (0, start))
    while heap:
        nowTime, nowPosition = heapq.heappop(heap)
        if timeTable[nowPosition] < nowTime:
            continue
        for nextPosition, nextTime in graph[nowPosition]:
            stand = nowTime + nextTime
            if stand < timeTable[nextPosition]:
                timeTable[nextPosition] = stand
                heapq.heappush(heap, (stand, nextPosition))
    return timeTable


def hacking():
    global n, graph
    n, d, c = map(int, input().rstrip('\n').split())
    graph = {x: [] for x in range(1, n + 1)}
    ansCnt, ansTime = 0, 0
    for _ in range(d):
        a, b, s = map(int, input().rstrip('\n').split())
        graph[b].append([a, s])

    table = dijkstra(c)
    for i in table:
        if i != sys.maxsize:
            if ansTime < i:
                ansTime = i
            ansCnt += 1
    print(ansCnt, ansTime)


def solution():
    input = sys.stdin.readline
    t = int(input().rstrip('\n'))
    for _ in range(t):
        hacking()


solution()