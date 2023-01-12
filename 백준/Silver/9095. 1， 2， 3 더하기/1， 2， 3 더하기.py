def oneTwoThree():
    maxValue = 10
    temp = [0, 1, 2, 4]
    for _ in range(maxValue - 3):
        temp.append(temp[-1] + temp[-2] + temp[-3])
    return temp


def solution():
    t = int(input())
    ans = oneTwoThree()
    for _ in range(t):
        print(ans[int(input())])


solution()