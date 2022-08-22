def solution(s, n):
    answer = ''
    
    for i in s:
        if i.isalpha():
            temp = ord(i)
            if temp>96:
                temp = (temp-97+n)%26+97
            else:
                temp = (temp-65+n)%26+65
            answer+=chr(temp)
            
        else:
            answer+=' '
    
    return answer
