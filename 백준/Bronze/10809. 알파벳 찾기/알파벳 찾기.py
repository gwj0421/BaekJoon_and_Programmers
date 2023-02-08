import sys


def solution():
    input = sys.stdin.readline
    n = input().rstrip('\n')
    temp = ['-1' for _ in range(26)]
    for idx, value in enumerate(n):
        if temp[ord(value) - 97] == '-1':
            temp[ord(value) - 97] = str(idx)

    print(' '.join(temp))


solution()