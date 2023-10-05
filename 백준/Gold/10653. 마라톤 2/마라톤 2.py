import sys


def getDis(x1, y1, x2, y2):
    return abs(x1 - x2) + abs(y1 - y2)


def solution():
    input = sys.stdin.readline
    N, K = map(int, input().split())
    checkPoint = [tuple(map(int, input().split())) for _ in range(N)]

    distance = [[(sys.maxsize, 0) for _ in range(K + 1)] for _ in range(N)]
    distance[0][0] = (0, 0)  # dist, remain
    distance[1][0] = (getDis(*checkPoint[0], *checkPoint[1]), 0)  # dist, remain

    for i in range(2, N):
        for j in range(1, K + 2):
            for dist, remain, in distance[i - j]:
                if remain + j - 1 <= K:
                    tempDist = dist + getDis(*checkPoint[i - j], *checkPoint[i])
                    tempRemain = remain + j - 1
                    if tempDist < distance[i][remain + j - 1][0]:
                        distance[i][remain + j - 1] = (tempDist, tempRemain)

    print(min(distance[-1], key=lambda x: x[0])[0])


solution()