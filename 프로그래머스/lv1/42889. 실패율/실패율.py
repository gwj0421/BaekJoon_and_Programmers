def solution(N, stages):
    answer = {}
    remain=len(stages)
    for i in range(1,N+1):
        lose=stages.count(i)
        if lose==0:
            answer[i]=0
        else:
            answer[i]=lose/remain
        remain-=lose
        
    return sorted(answer, key=lambda x: answer[x],reverse=True)

'''def solution(N, stages):
    fail = {}
    for i in range(1,N+1):
        try:
            fail_ = len([a for a in stages if a==i])/len([a for a in stages if a>=i])
        except:
            fail_ = 0
        fail[i]=fail_
    answer = sorted(fail, key=fail.get, reverse=True)
    return answer'''