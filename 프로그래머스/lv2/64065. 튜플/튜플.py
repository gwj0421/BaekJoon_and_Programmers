import re
def solution(s):
    answer = []
    s=re.sub('[{}]','',s.replace('},',' ')).split()
    for i in range(len(s)):
        s[i]=list(map(int,s[i].split(',')))
    s=sorted(s,key=lambda x : len(x))
    if len(s)<2:
        return s[0]
    else:
        answer.append(s[0][-1])
        for i in s[1:]:
            answer.append(set(i).difference(answer).pop())
    return answer