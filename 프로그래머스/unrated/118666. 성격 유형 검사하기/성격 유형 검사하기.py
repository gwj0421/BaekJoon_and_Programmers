def solution(survey, choices):
    answer = ''
    temp = {'R':0,'T':0,'C':0,'F':0,'J':0,'M':0,'A':0,'N':0}
    for q,c in zip(survey,choices):
        if c==1 or c==2 or c==3:
            temp[q[0]]+=abs(c-4)
        elif c==5 or c==6 or c==7:
            temp[q[1]]+=c-4
    print(temp)
    if temp['R']>=temp['T']:
        answer+='R'
    else:
        answer+='T'
        
    if temp['C']>=temp['F']:
        answer+='C'
    else:
        answer+='F'
        
    if temp['J']>=temp['M']:
        answer+='J'
    else:
        answer+='M'
        
    if temp['A']>=temp['N']:
        answer+='A'
    else:
        answer+='N'
            
    return answer