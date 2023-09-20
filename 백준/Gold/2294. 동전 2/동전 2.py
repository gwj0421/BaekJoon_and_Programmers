import sys


# 1 - 1
# 2 - 1+1
# 3 - 1+1+1
# 4 - 1+1+1+1
# 5 - 5
# 6 - 5+1
# 7 - 5+1+1
# 8 - 5+1+1+1
# 9 - 5+1+1+1+1
# 10 - 5+5
# 11 - 5+5+1
# 12 - 12
# 13 - 12+1
# 14 - 12+1+1
# 15 - 5+5+5

def solution():
    input = sys.stdin.readline
    n, k = map(int, input().split())
    coins = [int(input().rstrip()) for _ in range(n)]
    coinCnt = [sys.maxsize for _ in range(k + 1)]
    coinCnt[0] = 0
    for coin in coins:
        for i in range(coin, k + 1):
            coinCnt[i] = min(coinCnt[i], coinCnt[i - coin] + 1)
    print(coinCnt[k] if coinCnt[k] != sys.maxsize else -1)


solution()