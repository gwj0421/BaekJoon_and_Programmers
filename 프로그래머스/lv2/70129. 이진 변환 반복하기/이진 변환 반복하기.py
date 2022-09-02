def solution(s):
    answer = []
    while s != '1':
        one_cnt = s.count('1')
        temp = '1'*one_cnt
        answer.append(len(s)-one_cnt)
        s = bin(len(temp))[2:]
    return [len(answer),sum(answer)]