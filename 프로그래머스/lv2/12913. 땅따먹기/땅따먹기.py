def solution(land):
    length = len(land)
    
    for i in range(1,length):
        for j in range(4):
            tmp = land[i-1][j]
            land[i-1][j] = -1
            land[i][j] += max(land[i-1][0],land[i-1][1],land[i-1][2],land[i-1][3])
            land[i-1][j] = tmp
    
    answer = max(land[-1])
            

    return answer