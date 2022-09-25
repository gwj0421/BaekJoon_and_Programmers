def solution(m, n, board):
    answer = 0
    board = [list(x) for x in zip(*board)]
    while True:
        next_board = board.copy()
        for _n in range(n-1):
            for _m in range(m-1):
                if board[_n][_m][0] == board[_n+1][_m][0] == board[_n][_m+1][0] == board[_n+1][_m+1][0] and board[_n][_m] != 'space':
                    next_board[_n][_m] = next_board[_n][_m]+'clear'
                    next_board[_n+1][_m] = next_board[_n+1][_m]+'clear'
                    next_board[_n][_m+1] = next_board[_n][_m+1]+'clear'
                    next_board[_n+1][_m+1] = next_board[_n+1][_m+1]+'clear'
        temp = 0
        for i in next_board:
            for j in i:
                if 'clear' in j:
                    temp+=1
        
        if temp > 0:
            answer += temp
            board = [[x for x in y if 'clear' not in x] for y in board]
            for i in range(len(board)):
                for j in range(m-len(board[i])):
                    board[i].insert(0,'space')
        else:
            break
    return answer