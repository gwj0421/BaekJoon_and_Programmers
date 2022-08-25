def solution(n, words):
    answer = []
    told = [words[0]]
    
    for i in range(1,len(words)):
        cnt = divmod(i,n)
        if words[i] in told or words[i-1][-1] != words[i][0]:
            return [cnt[1]+1,cnt[0]+1]
        else:
            told.append(words[i])
            
    return [0,0]