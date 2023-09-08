import sys


def dfs(y, x, value, visited):
    global maxVal
    if x == M:
        dfs(y + 1, 0, value, visited)
    elif y == N:
        maxVal = max(maxVal, value)
        return
    else:
        if not visited[y][x]:
            for d1 in range(4):
                d2 = (d1 + 1) % 4
                y1, x1, y2, x2 = y + controlY[d1], x + controlX[d1], y + controlY[d2], x + controlX[d2],
                if -1 < y1 < N and -1 < y2 < N and -1 < x1 < M and -1 < x2 < M:
                    if not visited[y1][x1] and not visited[y2][x2]:
                        visited[y][x], visited[y1][x1], visited[y2][x2] = True, True, True
                        dfs(y, x + 1, value + board[y][x] * 2 + board[y1][x1] + board[y2][x2], visited)
                        visited[y][x], visited[y1][x1], visited[y2][x2] = False, False, False

        dfs(y, x + 1, value, visited)


input = sys.stdin.readline
N, M = map(int, input().split())
maxVal = 0
board = [list(map(int, input().split())) for _ in range(N)]
controlY, controlX = [0, 1, 0, -1], [1, 0, -1, 0]

dfs(0, 0, 0, [[False for _ in range(M)] for _ in range(N)])
print(maxVal)