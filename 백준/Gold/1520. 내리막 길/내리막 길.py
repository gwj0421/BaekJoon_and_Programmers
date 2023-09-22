import sys


def solution():
    def dfs(y, x):
        if y == m - 1 and x == n - 1:
            return 1
        if dp[y][x] == -1:
            dp[y][x] = 0
            for d in range(4):
                ny, nx = y + controlY[d], x + controlX[d],
                if -1 < ny < m and -1 < nx < n and board[ny][nx] < board[y][x]:
                    dp[y][x] += dfs(ny, nx)

        return dp[y][x]

    input = sys.stdin.readline
    sys.setrecursionlimit(10 ** 6)

    m, n = map(int, input().split())
    board = [list(map(int, input().split())) for _ in range(m)]
    controlY, controlX = [0, 0, 1, -1], [1, -1, 0, 0]
    dp = [[-1 for _ in range(n)] for _ in range(m)]

    print(dfs(0, 0))


solution()