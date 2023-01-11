def solution():
    n = int(input())
    tile = [0, 1, 2]
    if n < 3:
        print(tile[n])
        return
    for _ in range(n - 2):
        tile.append(tile[-1] + tile[-2])
    print(tile[-1] % 10007)


solution()