"""
def solution(id_list, report, k):
    answer = [0 for i in range(len(id_list))]
    receive_list={i:'' for i in id_list}
    report=set(report)
    for i in report:
        id=i.split()
        receive_list[id[1]]+=id[0]+' '
    id_list={i:j for j,i in enumerate(id_list)}
     
    for i in receive_list.values():
        if len(i.split())>=k:
            for j in i.split():
                answer[id_list[j]]+=1
    
    return answer
"""

def solution(id_list, report, k):
    answer = [0 for i in range(len(id_list))]
    receive_list={i:0 for i in id_list}
    report=set(report)
    for i in report:
        receive_list[i.split()[1]]+=1
    
    for i in report:
        if receive_list[i.split()[1]]>=k:
            answer[id_list.index(i.split()[0])]+=1
    
    return answer