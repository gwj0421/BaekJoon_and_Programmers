from collections import deque
def solution(F,S,G,U,D):
    queue = deque([S])
    visited = [False]*(F+1)
    visited[S] = True
    cnt = [0]*(F+1)

    while queue:
        now = queue.popleft()
        if now == G:
            return cnt[G]
        next_up = now + U
        next_down = now - D
        if 0<next_up <= F and not visited[next_up]:
            queue.append(next_up)
            visited[next_up] = True
            cnt[next_up] = cnt[now]+1
        if 0 < next_down <= F and not visited[next_down]:
            queue.append(next_down)
            visited[next_down] = True
            cnt[next_down] = cnt[now]+1
        
    return "use the stairs"


F,S,G,U,D = map(int,input().split())
print(solution(F,S,G,U,D))