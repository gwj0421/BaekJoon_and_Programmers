import sys


def bellmanFord(start):
    timeTable = [INF for _ in range(n + 1)]
    timeTable[start] = 0
    for i in range(n):
        for edge in graph:
            node = edge[0]
            nextNode = edge[1]
            time = edge[2]
            if timeTable[nextNode] > timeTable[node] + time:
                timeTable[nextNode] = timeTable[node] + time
                if i == n - 1:
                    return True
    return False


def eachTest():
    global n, m, INF, graph
    n, m, w = map(int, input().split())
    graph = []

    for _ in range(m):
        rs, re, rt = map(int, input().split())
        graph.append([rs, re, rt])
        graph.append([re, rs, rt])
    for _ in range(w):
        ws, we, wt = map(int, input().split())
        graph.append([ws, we, -wt])

    if bellmanFord(1):
        print('YES')
    else:
        print('NO')


def solution():
    global INF
    input = sys.stdin.readline
    INF = sys.maxsize
    t = int(input().rstrip('\n'))
    for _ in range(t):
        eachTest()


solution()