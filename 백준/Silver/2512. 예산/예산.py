import sys


def sumBudget(upperLimit):
    result = 0
    for budget in budgets:
        if budget <= upperLimit:
            result += budget
        else:
            result += upperLimit
    return result


def paremtricSearch(start, end, target):
    while start <= end:
        mid = (start + end) // 2
        if sumBudget(mid) <= target:
            start = mid + 1
        else:
            end = mid - 1
    print(end)


def solution():
    global budgets, money
    input = sys.stdin.readline
    n = int(input().rstrip('\n'))
    budgets = list(map(int, input().split()))
    money = int(input().rstrip('\n'))
    if sum(budgets) <= money:
        print(max(budgets))
        return
    paremtricSearch(0, max(budgets), money)


solution()
