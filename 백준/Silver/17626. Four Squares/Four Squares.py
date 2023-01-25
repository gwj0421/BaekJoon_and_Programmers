import math
import sys

def checkInt(num):
    if int(math.sqrt(num)) == math.sqrt(num):
        return True
    return False


def calculate(n):
    if checkInt(n):
        print(1)
        return
    for i in range(1, int(math.sqrt(n)) + 1):
        if checkInt(n - i ** 2):
            print(2)
            return
    for i in range(1, int(math.sqrt(n)) + 1):
        for j in range(1, int(math.sqrt(n-i**2)) + 1):
            if checkInt(n - i ** 2 - j ** 2):
                print(3)
                return
    print(4)
    return


def solution():
    input = sys.stdin.readline
    n = int(input().rstrip('\n'))
    calculate(n)


solution()