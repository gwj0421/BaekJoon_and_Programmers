import sys
import heapq

N = int(sys.stdin.readline())
max_heap = []  # 중간값 이상의 값들을 저장
min_heap = []  # 중간값 미만의 값들을 저장

for _ in range(N):
    num = int(sys.stdin.readline())
    
    # max_heap과 min_heap의 크기를 맞추기 위해 삽입
    if len(max_heap) == len(min_heap):
        heapq.heappush(max_heap, -num)  # max_heap에 음수로 삽입
    else:
        heapq.heappush(min_heap, num)
    
    # max_heap의 최댓값이 min_heap의 최솟값보다 큰 경우, 값을 교환
    if min_heap and -max_heap[0] > min_heap[0]:
        max_value = -heapq.heappop(max_heap)
        min_value = heapq.heappop(min_heap)
        heapq.heappush(max_heap, -min_value)
        heapq.heappush(min_heap, max_value)
    
    # 중간값 출력
    print(-max_heap[0])
