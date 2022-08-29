from collections import Counter

def solution(citations):
    answer = -1
    paper_counter = Counter(citations)
    max_value = max(paper_counter.keys())
    
    for h in range(max_value,-1,-1):
        const1 = 0
        const2 = 0
        for key,value in paper_counter.items():
            if key >= h:
                const1 +=value
            else:
                const2 +=value
                
        if const1>=h and const2<=h:
            answer = h
            break

    return answer