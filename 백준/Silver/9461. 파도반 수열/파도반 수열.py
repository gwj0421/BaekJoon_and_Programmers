def searchTriangleSide(num):
    temp = [0, 1, 1, 1, 2, 2]
    if num < 6:
        print(temp[num])
    else:
        for _ in range(num - 5):
            temp.append(temp[-1] + temp[-5])
        print(temp[-1])


def solution():
    t = int(input())
    for _ in range(t):
        searchTriangleSide(int(input()))


solution()