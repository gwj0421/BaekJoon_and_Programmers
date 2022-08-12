def solution(numbers, target):
    graph = {'+':numbers[0],'-':-numbers[0]}
    temp = graph.copy()
    cnt = 0
    for i in numbers:
        for key,value in temp.items():
            graph[key+'+']=value+i
            graph[key+'-']=value-i
        temp = graph.copy()
                
    for key,value in graph.items():
        if len(key)==len(numbers) and value==target:
            cnt+=1
            
    return cnt