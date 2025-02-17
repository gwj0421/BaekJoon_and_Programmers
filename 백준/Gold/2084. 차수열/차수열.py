# 2084번 차수열
# 차수열, 그래프 이론, 구성적
'''
접근 방법:
일단 차수열을 정렬해
예를 들어 44432라면?
4부터 시작해서 분배해
3321
210
이후엔 불가능
이러한 과정을 반복하다가 불가능함을 판단하면 된다.
'''
import sys
input = sys.stdin.readline

N = int(input())
deg = list(map(int, input().rstrip().split()))
if sum(deg) % 2:
    print(-1)
    exit(0)
matrix = [[0 for _ in range(N)] for _ in range(N)]
while True:
    ordered_vertex = sorted(range(N), key = lambda x: deg[x], reverse = True)
    largest_vertex_num = ordered_vertex[0]
    largest_degree = deg[largest_vertex_num]
    if largest_degree == 0:
        break
    for i in range(1, largest_degree+1):
        if i >= N:
            print(-1)
            exit(0)
        vertex_num = ordered_vertex[i]
        if deg[vertex_num] == 0:
            print(-1)
            exit(0)
        deg[vertex_num] -= 1
        deg[largest_vertex_num] -= 1
        matrix[vertex_num][largest_vertex_num] = 1
        matrix[largest_vertex_num][vertex_num] = 1
for i in range(N):
    print(*matrix[i])
