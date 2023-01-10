def solution():
    n = int(input())
    pList = []
    for idx, value in enumerate(list(map(int, input().split())), start=1):
        pList.append([idx, value])
    pList.sort(key=lambda x: x[1], reverse=True)
    ans = 0
    for idx, value in enumerate(pList, start=1):
        ans += idx * value[1]
    print(ans)


solution()