from collections import deque
import sys

def bfs(graph, root) :
    visited = []
    queue = deque([root])

    while queue :
        cur_node = queue.popleft()
        if cur_node not in visited :
            visited.append(cur_node)
            queue.extend(graph[cur_node])
    
    return visited

def dfs(graph, root) :
    visited = []
    stack = [root]

    while stack :
        cur_node = stack.pop()
        if(cur_node not in visited) :
            visited.append(cur_node)
            stack.extend(reversed(graph[cur_node]))
    
    return visited

my_input = lambda : sys.stdin.readline().rstrip()

N, M, V = map(int,input().split(" "))

graph = {}

for i in range(1, N+1):
    graph[i] = []

for _ in range(M):
    s, d = map(int,input().split(" "))
    graph[s].append(d)
    graph[d].append(s)    

for k in graph.keys():
    graph[k].sort()

print(*dfs(graph, V))
print(*bfs(graph, V))