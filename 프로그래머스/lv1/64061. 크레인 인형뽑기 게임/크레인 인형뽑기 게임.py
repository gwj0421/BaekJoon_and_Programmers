def solution(board, moves):
    pit=[]
    answer=0
    
    for i in moves:
        for j in range(len(board)):
            if board[j][i-1]!=0:
                pit.append(board[j][i-1])
                board[j][i-1]=0
                if len(pit)>1:
                    if pit[-1]==pit[-2]:
                        pit[-2:]=[]
                        answer+=2
                break
    return answer