def hanoi(n,start,destination,via):
    if n<=1:
        move.append([start,destination])
        return 0
    hanoi(n-1,start,via,destination)
    move.append([start,destination])
    hanoi(n-1,via,destination,start)
    return 0

def solution(n):
    global move
    move = []
    hanoi(n,1,3,2)
    return move
