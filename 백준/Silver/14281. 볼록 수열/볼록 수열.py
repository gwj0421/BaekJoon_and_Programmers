import sys
input = sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))
cnt = 0

def back(n, arr):
    global cnt
    is_convex = True
    for i in range(1, n-1):
        if arr[i-1]+arr[i+1] < 2*arr[i]:
            tmp = arr[i]- (arr[i-1] + arr[i+1])//2
            arr[i] -= tmp
            cnt += tmp
            is_convex = False
    return cnt, is_convex

while True:
    cnt, is_convex = back(n, arr)
    if is_convex: 
        break
  
print(cnt)