from collections import deque
def checkError(n,h,garbage):
    for _h in range(h):
        for _n in range(n):
            if 0 in garbage[_h][_n]:
                return False
    return True

def bfs(m,n,h,garbage,visited,needVisited):
    day = 1
    controlx = [0,0,-1,1,0,0]
    controly = [0,0,0,0,1,-1]
    controlz = [1,-1,0,0,0,0]
    while needVisited:
        nowx,nowy,nowz = needVisited.popleft()
        for control in range(6):
            nextx,nexty,nextz = nowx+controlx[control],nowy+controly[control],nowz+controlz[control]
            if -1<nextx<m and -1<nexty<n and -1<nextz<h:
                if garbage[nextz][nexty][nextx] == 0 and not visited[nextz][nexty][nextx]:
                    garbage[nextz][nexty][nextx] = garbage[nowz][nowy][nowx] + 1
                    visited[nextz][nexty][nextx] = True
                    needVisited.append([nextx,nexty,nextz])
                    day = max(day,garbage[nextz][nexty][nextx])
    if checkError(n,h,garbage):
        print(day-1)
    else:
        print(-1)

def solution():
    m,n,h = map(int,input().split())
    garbage = []
    needVisited = deque()
    visited = [[[False for _ in range(m)] for _ in range(n)] for _ in range(h)]
    for _h in range(h):
        newStage = []
        for _n in range(n):
            info = list(map(int,input().split()))
            for _m in range(m):
                if info[_m] == 1:
                    needVisited.append([_m,_n,_h])
                    visited[_h][_n][_m] = True
            newStage.append(info)
        garbage.append(newStage)
    bfs(m,n,h,garbage,visited,needVisited)

solution()