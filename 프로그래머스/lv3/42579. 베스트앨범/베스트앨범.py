def solution(genres, plays):
    answer = []
    genres_list = list(set(genres))
    geners_name = {x:idx for idx,x in enumerate(genres_list)}
    temp = [[] for _ in range(len(genres_list))]

    idx = 0
    for gen,play in zip(genres,plays):
        temp[geners_name[gen]].append([idx,play])
        idx += 1
    temp.sort(reverse=True,key=lambda x : (sum([x[i][1] for i in range(len(x))])))
    print(temp)
    for i in temp:
        if len(i)>1:
            _max = sorted(i,reverse=True,key = lambda x : (x[1],-x[0]))
            print(_max)
            answer.append(_max[0][0])
            answer.append(_max[1][0])
        else:
            answer.append(i[0][0])

    return answer