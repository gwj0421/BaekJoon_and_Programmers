import sys
def Z(x, y, block_size):
    global cnt
    if x == c and y == r:
        print(cnt)
        sys.exit(0)
    if block_size == 1:
        cnt += 1
        return
    if not (x <= c < x + block_size and y <= r < y + block_size):
        cnt += block_size ** 2
        return

    half = block_size // 2
    Z(x, y, half)
    Z(x + half, y, half)
    Z(x, y + half, half)
    Z(x + half, y + half, half)

def solution():
    global r, c, cnt
    cnt = 0
    n, r, c = map(int, input().split())
    Z(0, 0, 2 ** n)

solution()
