def solution():
    n,m = map(int,input().split())
    set1 = set()
    set2 = set()
    for _ in range(n):
        name = input()
        set1.add(name)
    for _ in range(m):
        name = input()
        set2.add(name)
    output = sorted(list(set1 & set2))
    print(len(output))
    for i in output:
        print(i)

solution()