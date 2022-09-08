from collections import deque

def solution(s):
    s = deque(s)
    answer = ''
    first_flag = True
    
    while s:
        word = s.popleft()
        if word == ' ':
            answer += ' '
            first_flag = True
        else:
            if first_flag:
                if word.isdigit():
                    answer += word
                    first_flag = False
                else:
                    answer += word.upper()
                    first_flag = False
            else:
                answer += word.lower()
                
    return answer