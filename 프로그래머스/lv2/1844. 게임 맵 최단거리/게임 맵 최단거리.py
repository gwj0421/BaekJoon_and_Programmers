from collections import deque

def solution(maps):
    n,m = len(maps),len(maps[0])
    visited = [[-1]*m for _ in range(n)]
    visited[0][0] = 1
    d = [[1,0],[-1,0],[0,-1],[0,1]]

    start_x,start_y = 0,0
    queue = deque([[start_x,start_y]])
    while queue:
        p_x, p_y = queue.popleft()
        for i in range(4):
            next_x = p_x+d[i][0]
            next_y = p_y+d[i][1]

            if (-1<next_x<m) and (-1<next_y<n):
                if visited[next_y][next_x] == -1:
                    if maps[next_y][next_x] == 0:
                        visited[next_y][next_x] = 0
                    elif maps[next_y][next_x] == 1:
                        visited[next_y][next_x] = visited[p_y][p_x] + 1
                        queue.append([next_x,next_y])
                elif visited[next_y][next_x] > 0:
                    if visited[next_y][next_x] > visited[p_y][p_x] + 1:
                        visited[next_y][next_x] = visited[p_y][p_x] + 1
                        queue.append([next_x,next_y])
    return visited[-1][-1]