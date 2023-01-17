import heapq


def deleteOtherHeap(targetHeap):
    while targetHeap and not isAppend[targetHeap[0][1]]:
        heapq.heappop(targetHeap)


def deleteHeap(targetHeap):
    if targetHeap:
        isAppend[targetHeap[0][1]] = False
        heapq.heappop(targetHeap)


def dualQueue():
    global isAppend
    k = int(input())
    minHeap, maxHeap = [], []
    isAppend = [False for _ in range(1000001)]
    for priority in range(k):
        order, num = input().split()
        if order.startswith('I'):
            heapq.heappush(minHeap, (int(num), priority))
            heapq.heappush(maxHeap, (int(num) * -1, priority))
            isAppend[priority] = True
        elif order.startswith('D'):
            if num == '1':
                deleteOtherHeap(maxHeap)
                deleteHeap(maxHeap)
            elif num == '-1':
                deleteOtherHeap(minHeap)
                deleteHeap(minHeap)
    deleteOtherHeap(maxHeap)
    deleteOtherHeap(minHeap)
    if maxHeap and minHeap:
        print(-heapq.heappop(maxHeap)[0], heapq.heappop(minHeap)[0])
    else:
        print("EMPTY")


def solution():
    t = int(input())
    for _ in range(t):
        dualQueue()


solution()