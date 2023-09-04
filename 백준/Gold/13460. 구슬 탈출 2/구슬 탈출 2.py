import sys
from collections import deque


def moveBall(board, x, y, d):
    moveCnt = 0
    while board[y + controlY[d]][x + controlX[d]] != '#' and board[y][x] != 'O':
        x, y = x + controlX[d], y + controlY[d]
        moveCnt += 1

    return x, y, moveCnt


def playGame(board, rx, ry, bx, by):
    visited = set((rx, ry, bx, by))
    needVisited = deque([[rx, ry, bx, by, 1]])
    while needVisited:
        nrx, nry, nbx, nby, totalMoveCnt = needVisited.popleft()
        if totalMoveCnt > 10:
            break
        for i in range(4):
            nerx, nery, rmc = moveBall(board, nrx, nry, i)
            berx, bery, bmc = moveBall(board, nbx, nby, i)

            if board[bery][berx] != 'O':
                if board[nery][nerx] == 'O':
                    print(totalMoveCnt)
                    return
                if nerx == berx and nery == bery:
                    if rmc > bmc:
                        nerx -= controlX[i]
                        nery -= controlY[i]
                    elif rmc < bmc:
                        berx -= controlX[i]
                        bery -= controlY[i]
                    else:
                        print("error")
                        sys.exit()

                if (nerx, nery, berx, bery) not in visited:
                    visited.add((nerx, nery, berx, bery))
                    needVisited.append([nerx, nery, berx, bery, totalMoveCnt + 1])
    print(-1)


def solution():
    global n, m, controlX, controlY
    input = sys.stdin.readline
    n, m = map(int, input().split())
    controlX, controlY = [0, 1, 0, -1], [-1, 0, 1, 0]
    board = []
    redX, redY, blueX, blueY = 0, 0, 0, 0

    for i in range(n):
        eachLine = list(input().rstrip('\n'))
        for j in range(m):
            if eachLine[j] == 'R':
                redX, redY = j, i
            elif eachLine[j] == 'B':
                blueX, blueY = j, i
        board.append(eachLine)
    playGame(board, redX, redY, blueX, blueY)


solution()