def checkDuplicate(paperSize, x, y):
    stand = paper[y][x]
    for i in range(y, y + paperSize):
        for j in range(x, x + paperSize):
            if stand != paper[i][j]:
                return True, -1
    return False, stand


def cutPaper(paperSize, x, y):
    flag, value = checkDuplicate(paperSize, x, y)
    if paperSize == 1:
        score[paper[y][x]] += 1
    elif not flag:
        score[value] += 1
    else:
        half = paperSize // 3
        for i in range(3):
            for j in range(3):
                cutPaper(half, x + half * j, y + half * i)


def solution():
    global paper, score
    n = int(input())
    paper = []
    score = {-1: 0, 0: 0, 1: 0}
    for _ in range(n):
        paper.append(list(map(int, input().split())))
    cutPaper(n, 0, 0)
    print(score[-1], score[0], score[1], sep='\n')


solution()
