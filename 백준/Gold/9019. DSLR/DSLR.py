from collections import deque
import sys
input = sys.stdin.readline
def D(num):
    num *= 2
    if num > 9999:
        num %= 10000
    return num


def S(num):
    if num == 0:
        return 9999
    return num - 1


def R(num):
    return num % 10 * 1000 + num // 10


def L(num):
    return num % 1000 * 10 + num // 1000


def bfs(before, after):
    visited = set()
    visited.add(before)
    needVisited = deque([[before, '']])
    while needVisited:
        nowNum, nowCommand = needVisited.popleft()
        if nowNum == after:
            print(nowCommand)
            return
        nextNum = D(nowNum)
        if nextNum not in visited:
            visited.add(nextNum)
            needVisited.append([nextNum, nowCommand+'D'])
        nextNum = S(nowNum)
        if nextNum not in visited:
            visited.add(nextNum)
            needVisited.append([nextNum, nowCommand+'S'])
        nextNum = L(nowNum)
        if nextNum not in visited:
            visited.add(nextNum)
            needVisited.append([nextNum, nowCommand+'L'])
        nextNum = R(nowNum)
        if nextNum not in visited:
            visited.add(nextNum)
            needVisited.append([nextNum, nowCommand+'R'])
    print(visited)


def solution():
    t = int(input())
    for _ in range(t):
        before, after = map(int,input().split())
        bfs(before, after)


solution()