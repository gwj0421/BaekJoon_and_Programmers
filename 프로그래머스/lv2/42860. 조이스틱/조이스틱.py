def check(value,target):
    stand = 65
    temp1 = abs(ord(target)-ord(value))
    temp2 = abs(ord(value)-ord(target)+26)
    return min(temp1,temp2)

def solution(name):
    answer = 0
    for i in range(len(name)-1):
        answer+=check('A',name[i])
    answer += check('A',name[-1])
    
    move = len(name)-1
    for i in range(len(name)):
        next_i = i+1
        while (next_i < len(name)) and (name[next_i]=='A'):
            next_i += 1
        distance = min(i,len(name)-next_i)
        move = min(move,i+len(name)-next_i+distance)
        
    answer+=move
    return answer
solution("AAAABAAAAAAKSAIQ")