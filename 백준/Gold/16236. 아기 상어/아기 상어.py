from collections import deque


def bfs(x, y):
    controlx = [1, -1, 0, 0]
    controly = [0, 0, 1, -1]

    visited = [[False for _ in range(n)] for _ in range(n)]
    visited[y][x] = True
    needVisited = deque([[x, y, 0]])
    eatenFish = []
    while needVisited:
        nowX, nowY, nowTime = needVisited.popleft()
        for i in range(4):
            nextX, nextY, nextTime = nowX + controlx[i], nowY + controly[i], nowTime + 1
            if -1 < nextX < n and -1 < nextY < n and not visited[nextY][nextX]:
                if occen[nextY][nextX] <= sharkSize:
                    visited[nextY][nextX] = True
                    needVisited.append([nextX, nextY, nextTime])
                    if occen[nextY][nextX] < sharkSize and occen[nextY][nextX] != 0:
                        eatenFish.append([nextX, nextY, nextTime])
    return sorted(eatenFish, key=lambda x: (-x[2], -x[1], -x[0]))


def move(x, y):
    global sharkSize
    eatFish, eatTime, sharkSize = 0, 0, 2
    while True:
        eatableFish = bfs(x, y)
        if len(eatableFish) == 0:
            break

        nextX, nextY, nextTime = eatableFish.pop()
        eatTime += nextTime
        occen[y][x] = 0
        occen[nextY][nextX] = 0
        x, y = nextX, nextY
        eatFish += 1
        if eatFish == sharkSize:
            sharkSize += 1
            eatFish = 0
    print(eatTime)


def solution():
    global n, occen
    n = int(input())
    occen = []
    babySharkX, babySharkY = -1, -1
    for i in range(n):
        temp = list(map(int, input().split()))
        for j in range(n):
            if temp[j] == 9:
                babySharkX, babySharkY = j, i
        occen.append(temp)
    move(babySharkX, babySharkY)


solution()