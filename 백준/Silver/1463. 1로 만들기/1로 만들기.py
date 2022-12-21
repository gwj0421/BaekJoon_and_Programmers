def solution():
    x = int(input())
    calList = [0 for _ in range(x+1)]

    for i in range(2,x+1):
        calList[i] = calList[i-1]+1
        if i % 3 == 0:
            calList[i] = min(calList[i],calList[i//3]+1)
        if i % 2 == 0:
            calList[i] = min(calList[i],calList[i//2]+1)
    print(calList[x])
solution()