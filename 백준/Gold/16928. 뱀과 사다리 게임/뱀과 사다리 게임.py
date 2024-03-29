from collections import deque


def bfs(start):
    dice = [1, 2, 3, 4, 5, 6]
    visited = set()
    visited.add(start)
    needVisited = deque([[start, 0]])
    while needVisited:
        nowPosition, nowCnt = needVisited.popleft()
        if nowPosition == 100:
            print(nowCnt)
            return
        for i in dice:
            nextPosition, nextCnt = nowPosition + i, nowCnt + 1
            if nextPosition < 101 and nextPosition not in visited:
                visited.add(nextPosition)
                if ladder.get(nextPosition):
                    nextPosition = ladder.get(nextPosition)
                    needVisited.append([nextPosition, nextCnt])
                    visited.add(nextPosition)
                else:
                    needVisited.append([nextPosition, nextCnt])


def solution():
    global ladder
    n, m = map(int, input().split())
    ladder = {}
    for _ in range(n + m):
        start, end = map(int, input().split())
        ladder[start] = end
    bfs(1)


solution()