def dfs(n,graph,start,visited):
    if visited[start]:
        pass
    else:
        visited[start] = True
        for i in range(n):
            if graph[start][i] == 1 and i != start and visited:
                visited = dfs(n,graph,i,visited)
    return visited

def solution(n, computers):
    answer = 0
    visited = [False for _ in range(n)]
    for start_idx in range(n):
        if visited[start_idx] == False:
            visited = dfs(n,computers,start_idx,visited)
            answer += 1

    return answer