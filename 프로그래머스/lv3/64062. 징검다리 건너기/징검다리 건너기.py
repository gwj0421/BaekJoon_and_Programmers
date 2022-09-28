def calc(stones, k, mid):
    now = 0
    for stone in stones:
        if (stone < mid): # stone - mid 가 0이면 이번 회엔 건널 수 있다는 것임
            now += 1
            # 즉 stone < mid 이면 전 사람 건널 때 0이 되어서 못 건너게 됐다는 것.
            # 건너 뛰는 것 값을 + 1 해준다.
        else:
            now = 0
        if(now >= k):
            return False
    return True
def solution(stones, k):
    if k==1: # 예외처리
        return min(stones)
    
    left = 1 # 최소 1명은 건널 수 있음
    right = max(stones) # 최고 값 +1, 그래야 두개 더해서 // 할 때 내림을 하게 되기 때문에 제대로 됨

    while(left < right - 1):
        # 1차이 날 땐 더 이상 가운데가 없이 때문에 left 쪽이 답이다.
        mid = (left + right) // 2
        if (calc(stones, k, mid)): # mid 가 가능한지 확인
            left = mid
        else:
            right = mid
    return left