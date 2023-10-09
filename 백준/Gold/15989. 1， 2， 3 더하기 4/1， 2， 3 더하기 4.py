import sys


# 1 - 1
# 2 - 11, 2
# 3 - 111, 12, 3
# 4 - 1111, 112, 22, 13
# 5 - 11111, 1112, 122, 113, 23
# 6 - 111111, 11112, 1122, 222, 1113, 123, 33
# 7 - 1111111, 111112, 11122, 1222, 11113, 1123, 223, 133
def solution():
    input = sys.stdin.readline
    T = int(input().rstrip())
    numbers = [int(input().rstrip()) for _ in range(T)]
    maxNum = max(numbers)

    arr = [0 for _ in range(maxNum + 1)]

    for i in [1, 2, 3]:
        arr[i] += 1
        for j in range(i + 1, maxNum + 1):
            arr[j] += arr[j - i]
            
    for i in numbers:
        print(arr[i])


solution()