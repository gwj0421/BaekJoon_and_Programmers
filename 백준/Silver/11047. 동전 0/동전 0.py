def solution():
    n, k = map(int, input().split())
    coins = []
    ans = 0
    for _ in range(n):
        coins.append(int(input()))
    for i in coins[::-1]:
        if i <= k:
            ans += k // i
            k %= i
    print(ans)


solution()