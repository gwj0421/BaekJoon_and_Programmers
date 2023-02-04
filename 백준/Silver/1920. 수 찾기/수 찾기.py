import sys


def solution():
    input = sys.stdin.readline
    n = int(input().rstrip('\n'))
    nums = set(list(map(int, input().split())))
    m = int(input().rstrip('\n'))
    questions = list(map(int, input().split()))

    for q in questions:
        if q in nums:
            print(1)
        else:
            print(0)


solution()