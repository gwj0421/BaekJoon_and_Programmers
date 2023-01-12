from collections import deque


def bfs(startx, starty):
    if board[starty][startx] == '0' or (board[starty][startx] == '1' and visited[starty][startx]):
        return
    needVisited = deque([[startx, starty]])
    controlx = [1, -1, 0, 0]
    controly = [0, 0, 1, -1]
    visited[starty][startx] = True
    cnt = 1
    while needVisited:
        nowx, nowy = needVisited.popleft()
        for i in range(4):
            nextx, nexty = nowx + controlx[i], nowy + controly[i]
            if -1 < nextx < n and -1 < nexty < n:
                if not visited[nexty][nextx] and board[nexty][nextx] == '1':
                    visited[nexty][nextx] = True
                    needVisited.append([nextx, nexty])
                    cnt += 1
    ans.append(cnt)


def solution():
    global board, ans, n, visited
    n = int(input())
    board = []
    ans = []
    visited = [[False for _ in range(n)] for _ in range(n)]
    for i in range(n):
        board.append(list(input()))
    for i in range(n):
        for j in range(n):
            bfs(j, i)
    print(len(ans))
    for i in sorted(ans):
        print(i)


solution()