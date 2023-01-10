def dfs1(x, y, cnt, maxValue, visited):
    global ans
    if cnt == 4:
        ans = max(ans, maxValue)
        return
    for i in range(4):
        nextx, nexty = x + controlx[i], y + controly[i]
        if -1 < nextx < m and -1 < nexty < n:
            if not visited[nexty][nextx]:
                visited[nexty][nextx] = True
                dfs1(nextx, nexty, cnt + 1, maxValue + board[nexty][nextx], visited)
                visited[nexty][nextx] = False


def dfs2(x, y):
    global ans
    patten = [[0, 1, 2], [1, 2, 3], [2, 3, 0], [3, 0, 1]]
    for i in patten:
        flag = True
        for j in i:
            if not (-1 < x + controlx[j] < m and -1 < y + controly[j] < n):
                flag = False
                break
        if flag:
            temp = board[y][x]
            for j in i:
                temp += board[y + controly[j]][x + controlx[j]]
            ans = max(ans, temp)


def solution():
    global n, m, board, controlx, controly, ans
    n, m = map(int, input().split())
    board = []
    controlx = [1, -1, 0, 0]
    controly = [0, 0, 1, -1]
    visited = [[False for _ in range(m)] for _ in range(n)]
    ans = -1
    for _ in range(n):
        board.append(list(map(int, input().split())))
    for i in range(n):
        for j in range(m):
            visited[i][j] = True
            dfs1(j, i, 1, board[i][j], visited)
            visited[i][j] = False
            dfs2(j, i)
    print(ans)


solution()