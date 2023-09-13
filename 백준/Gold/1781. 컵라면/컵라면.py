import sys, heapq

input = sys.stdin.readline
N = int(input().rstrip())
problems = []
for _ in range(N):
    deadline, noodle = map(int, input().split())
    problems.append((deadline,noodle))
problems.sort()

queue = []
for d,n in problems:
    heapq.heappush(queue,n)
    if d < len(queue):
        heapq.heappop(queue)

print(sum(queue))