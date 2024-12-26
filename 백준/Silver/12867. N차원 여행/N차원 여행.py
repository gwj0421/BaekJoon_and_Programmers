N = int(input())
M = int(input())
dims = list(map(int, input().split()))
dir = input()

able = True
path = [{}]
cur = {}

for i in range(M):
    cur[dims[i]] = cur.get(dims[i], 0) + (1 if dir[i] == '+' else -1)
    if cur[dims[i]] == 0:               # 위치가 0 인 차원은 딕셔너리에서 제거함으로써 모든 차원 표현할 필요 없음)
        del cur[dims[i]]
    for cmp in path:
        if cur.items() == cmp.items():  # 지나왔던 경로에 현재 위치가 존재하면 불가능
            able = False
            break
    if not able:
        break
    path.append(cur.copy())             # 경로에 현재 위치 추가

if cur == {}: able = False      # 마지막이 원점일 경우에도 불가능
print(1 if able else 0)

