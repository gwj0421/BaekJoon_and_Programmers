import sys


def check(deviceCnt):
    # 어떠한 공유기 쌍의 최대 거리일 때, 만약 공유기 개수가 부족하다면 최대 거리를 줄여야 함 -> end = mid - 1
    # 반대로, 공유기 개수가 충분하다면 최대 거리를 늘려도 됨 -> start = mid + 1
    if deviceCnt < C:
        return True
    return False


def settingDevice(houses, maxDiff, mid):
    deviceDiff, deviceCnt = maxDiff, 1
    nowDevice = houses[0]
    for i in range(1, N):
        if houses[i] - nowDevice >= mid:
            deviceDiff = min(deviceDiff, houses[i] - nowDevice)
            deviceCnt += 1
            nowDevice = houses[i]
    return deviceCnt, deviceDiff


def solution():
    global N, C
    input = sys.stdin.readline
    N, C = map(int, input().split())
    houses = sorted([int(input().rstrip('\n')) for _ in range(N)])

    # 매개변수 탐색, 탐색 대상 : 인접한 공유기 쌍의 최대 거리, 조건 : 공유기 개수

    maxDiff = houses[-1] - houses[0]
    start, end = 1, maxDiff
    ans = 1
    while start <= end:
        mid = (start + end) // 2
        deviceCnt, deviceDiff = settingDevice(houses, maxDiff, mid)
        if check(deviceCnt):
            end = mid - 1
        else:
            ans = max(ans, deviceDiff)
            start = mid + 1

    print(ans)


solution()