def checkDuplicate(paperSize, x, y):
    temp = set()
    for i in board[y:y + paperSize]:
        temp = temp | set(i[x:x + paperSize])
    if len(temp) > 1:
        return True
    return False


def checkOneOrZero(x, y):
    paperCnt[board[y][x]] += 1


def makePaper(paperSize, x, y):
    if not checkDuplicate(paperSize, x, y):
        checkOneOrZero(x, y)
    else:
        half = paperSize // 2
        makePaper(half, x, y)
        makePaper(half, x + half, y)
        makePaper(half, x, y + half)
        makePaper(half, x + half, y + half)


def solution():
    global paperCnt, board
    n = int(input())
    board = []
    paperCnt = [0, 0]
    for _ in range(n):
        board.append(list(map(int, input().split())))
    makePaper(n, 0, 0)
    print(paperCnt[0], paperCnt[1], sep='\n')


solution()