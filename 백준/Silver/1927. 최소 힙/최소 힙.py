import sys
import heapq


input = sys.stdin.readline


def solution():
    n = int(input())
    heap = []
    heapq.heapify(heap)
    for _ in range(n):
        temp = int(input())
        if temp == 0:
            if heap:
                print(heapq.heappop(heap))
            else:
                print(0)
        else:
            heapq.heappush(heap, temp)


solution()