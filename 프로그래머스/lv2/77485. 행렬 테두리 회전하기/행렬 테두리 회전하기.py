def change(arr,x1,y1,x2,y2):
    min_list = []
    # 윗면
    up = arr[x1-1][y1-1:y2-1]
    # 아랫면
    down = arr[x2-1][y1:y2]
    
    # 오른쪽 옆면
    for x in range(x2-2,x1-2,-1):
        arr[x+1][y2-1] = arr[x][y2-1]
        min_list.append(arr[x][y2-1])
        
    # 왼쪽 옆면
    for x in range(x1,x2):
        arr[x-1][y1-1] = arr[x][y1-1]
        min_list.append(arr[x][y1-1])
    
    arr[x1-1][y1:y2] = up
    arr[x2-1][y1-1:y2-1] = down
    
    return arr,min(min_list+up+down)

def solution(rows, columns, queries):
    arr = [[0] * columns for _ in range(rows)]
    result = []
    cnt = 0
    for i in range(rows):
        for j in range(columns):
            cnt += 1
            arr[i][j] = cnt
    
    for x1,y1,x2,y2 in queries:
        arr,temp=change(arr,x1,y1,x2,y2)
        result.append(temp)
        
    return result