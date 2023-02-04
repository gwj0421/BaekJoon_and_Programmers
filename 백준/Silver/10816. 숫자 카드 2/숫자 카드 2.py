import sys
from collections import Counter

def solution():
    input = sys.stdin.readline
    n = int(input().rstrip('\n'))
    numCards = list(map(int, input().split()))
    m = int(input().rstrip('\n'))
    questions = list(map(int, input().split()))

    cnt = Counter(numCards)
    ans = []
    for q in questions:
        if cnt.get(q):
            ans.append(cnt.get(q))
        else:
            ans.append(0)
    print(*ans)


solution()