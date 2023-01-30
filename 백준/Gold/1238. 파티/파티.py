import sys
import heapq


def makeVisited(start):
    temp = [float('inf') for _ in range(n + 1)]
    temp[start] = 0
    return temp


def dijkstra(start):
    heap = []
    heapq.heappush(heap, [0, start])
    visitedAndTime = makeVisited(start)
    while heap:
        time,now  = heapq.heappop(heap)
        if visitedAndTime[now] < time:
            continue
        for next, nextTime, in roads[now]:
            if time + nextTime < visitedAndTime[next]:
                visitedAndTime[next] = time + nextTime
                heapq.heappush(heap, [time + nextTime,next])
    return visitedAndTime


def solution():
    global n, x, roads
    input = sys.stdin.readline
    n, m, x = map(int, input().rstrip('\n').split())
    roads = {x: [] for x in range(1, n + 1)}
    for _ in range(m):
        start, end, time = map(int, input().rstrip('\n').split())
        roads[start].append([end, time])

    ans = dijkstra(x)
    ans[0] = 0
    for i in range(1, n + 1):
        if i != x:
            go = dijkstra(i)
            ans[i] += go[x]

    print(max(ans))
    
solution()