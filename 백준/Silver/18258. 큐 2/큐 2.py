import sys
from collections import deque


def solution():
    input = sys.stdin.readline
    N = int(input().rstrip())
    queue = deque()
    for _ in range(N):
        order = input().rstrip()
        if order == 'pop':
            if queue:
                print(queue.popleft())
            else:
                print(-1)
        elif order == 'size':
            print(len(queue))
        elif order == 'empty':
            print(0 if queue else 1)
        elif order == 'front':
            print(queue[0] if queue else -1)
        elif order == 'back':
            print(queue[-1] if queue else -1)
        else:
            x = int(order[5:])
            queue.append(x)


solution()