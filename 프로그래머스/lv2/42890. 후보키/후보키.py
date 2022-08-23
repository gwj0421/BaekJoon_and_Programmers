from itertools import combinations

def solution(relation):
    answer = 0
    length = len(relation[0])
    cnt = len(relation)
    comb_list = []
    col = [x for x in range(length)]
    for i in range(1,length+1):
        comb_list.extend(list(combinations(col,i)))
        
    while comb_list:
        value = comb_list.pop(0)
        t = [[] for _ in range(cnt)]
        for i in range(cnt):
            for j in value:
                t[i].append(relation[i][j])
        if cnt == len(list(set(map(tuple,t)))):
            answer +=1
            remove_list = []
            for i in range(len(comb_list)):
                if set(value).issubset(comb_list[i]):
                    remove_list.append(i)
            for i in remove_list[::-1]:
                comb_list.pop(i)
        else:
            pass
        
        
    return answer
