#2164
from collections import deque
inp=int(input())
queue=deque()
for i in range(1,inp+1):
    queue.append(i)
while len(queue)>1:
    queue.popleft()
    queue.append(queue.popleft())
print(queue[0])
