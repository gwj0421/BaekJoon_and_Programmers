def solution(s):
    s = sorted(list(map(int,s.split(' '))))
    answer = " ".join(map(str,[s[0],s[-1]]))
    
    return answer