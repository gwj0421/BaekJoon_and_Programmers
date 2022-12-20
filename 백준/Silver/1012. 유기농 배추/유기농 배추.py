import sys
from collections import deque

def bfs(start_x,start_y,m,n,table):
    step_x = [1,-1,0,0]
    step_y = [0,0,1,-1]
    tempNeedVisited = deque([[start_x,start_y]])
    tempVisited = deque([[start_x,start_y]])
    while tempNeedVisited:
        now_x,now_y = tempNeedVisited.popleft()
        for i in range(4):
            next_x,next_y = now_x+step_x[i],now_y+step_y[i]
            if -1<next_x<m and -1<next_y<n:
                if table[next_y][next_x]==1 and [next_x,next_y] not in tempVisited:
                    tempNeedVisited.append([next_x,next_y])
                    tempVisited.append([next_x,next_y])
    return tempVisited

def solution():
    t = int(input())
    insetCnt = [0 for _ in range(t)]
    for _t in range(t):
        m,n,k = map(int,input().split())
        table = [[0 for _ in range(m)] for _ in range(n)]
        need_visited = deque()
        visited = deque()
        for _ in range(k):
            mine_x,mine_y = map(int,input().split())
            need_visited.append([mine_x,mine_y])
            table[mine_y][mine_x] = 1

        while need_visited:
            start_x,start_y = need_visited.popleft()
            if [start_x,start_y] in visited:
                pass
            else:
                insetCnt[_t] += 1
                visited.extend(bfs(start_x,start_y,m,n,table))
    for i in insetCnt:
        print(i)
solution()