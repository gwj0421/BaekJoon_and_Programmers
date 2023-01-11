def solution():
    n = int(input())
    coordinate = list(map(int,input().split()))
    temp = list(set(coordinate))
    order = {value:str(idx) for idx,value in enumerate(sorted(temp))}
    ans = []
    for i in coordinate:
        ans.append(order[i])
    print(' '.join(ans))

solution()