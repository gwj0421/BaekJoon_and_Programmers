from collections import deque

def bfs(start,end,board):
    needVisited = deque([start])
    control = [lambda x:x+1,lambda x:x-1,lambda x:x*2]
    while needVisited:
        now = needVisited.popleft()
        if now == end:
            print(board[now])
            break
        for i in range(3):
            next = control[i](now)
            if -1<next<MAX and not board[next]:
                needVisited.append(next)
                board[next] = board[now] + 1
    # for idx,value in enumerate(board[:30]):
    #     print(idx,value)
def solution():
    global MAX
    MAX = 10 ** 5 + 1
    n,k = map(int,input().split())
    board = [0] + [0 for _ in range(MAX)]
    #board[n] = 0
    bfs(n,k,board)

solution()