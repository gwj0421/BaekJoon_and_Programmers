import sys

def checkCol(num,x):
    for i in range(9):
        if sudoku[i][x] == num:
            return False
    return True

def checkRow(num,y):
    for i in range(9):
        if sudoku[y][i] == num:
            return False
    return True

def checkSquare(num, y, x):
    y, x = y // 3 * 3, x // 3 * 3
    for i in range(y, y + 3):
        for j in range(x, x + 3):
            if sudoku[i][j] == num:
                return False
    return True


def dfs(blankIdx):
    if blankIdx == len(blank):
        for i in sudoku:
            print(*i)
        sys.exit(0)

    for num in range(1,10):
        y, x = blank[blankIdx]
        if checkCol(num,x) and checkRow(num,y) and checkSquare(num,y,x):
            sudoku[y][x] = num
            dfs(blankIdx + 1)
            sudoku[y][x] = 0


input = sys.stdin.readline
size = 9
sudoku, blank = [], []
for i in range(size):
    temp = list(map(int, input().split()))
    for j in range(size):
        if temp[j] == 0:
            blank.append([i, j])
    sudoku.append(temp)

dfs(0)
