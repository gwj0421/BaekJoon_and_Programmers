from collections import deque
import sys
result=deque()
for i in range(int(sys.stdin.readline())):
    order=list(map(str,sys.stdin.readline().split()))
    if order[0]=='push':
        result.append(order[1])
    elif order[0]=='pop':
        if not result:
            print(-1)
        else:
            print(result.pop())
    elif order[0]=='size':
        print(len(result))
    elif order[0]=='empty':
        if not result:
            print(1)
        else :
            print(0)
    elif order[0]=='top':
        if not result:
            print(-1)
        else :
            print(result[-1])
