import sys


def solution():
    def binarySearch():
        start, end, liquidSum = 0, N - 1, sys.maxsize
        ansStart, ansEnd = 0, N - 1
        while start < end:
            tempSum = liquid[start] + liquid[end]
            if liquidSum >= abs(tempSum):
                liquidSum = abs(tempSum)
                ansStart, ansEnd = start, end

            if tempSum > 0:
                end -= 1
            elif tempSum < 0:
                start += 1
            else:
                print(liquid[start], liquid[end])
                sys.exit()

        print(liquid[ansStart], liquid[ansEnd])

    input = sys.stdin.readline
    N = int(input().rstrip())
    liquid = list(map(int, input().split()))
    binarySearch()


solution()