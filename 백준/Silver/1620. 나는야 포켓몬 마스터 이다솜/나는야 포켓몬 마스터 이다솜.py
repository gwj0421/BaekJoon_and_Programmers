import sys


def solution():
    n, m = map(int, input().split())
    pokemonStrToNum = {}
    pokemonNumToStr = {}
    for idx in range(1, n + 1):
        pokemonName = input().rstrip('\n')
        pokemonNumToStr[idx] = pokemonName
        pokemonStrToNum[pokemonName] = idx
    for _ in range(m):
        problem = input().rstrip('\n')
        if problem.isdecimal():
            print(pokemonNumToStr[int(problem)])
        else:
            print(pokemonStrToNum[problem])

input = sys.stdin.readline
solution()