from itertools import combinations

# def solution(clothes):
#     answer = 0
#     combination = {}
#     for value,key in clothes:
#         if key not in combination:
#             combination[key] = [value]
#         else:
#             combination[key].append(value)
        
#     for i in range(1,len(combination.keys())+1):
#         temp1 = 0
#         for j in list(combinations(combination,i)):
#             temp2 = 1
#             for k in j:
#                 temp2 *= len(combination[k])
#             temp1 +=temp2
#         answer+= temp1
#     return answer

def solution(clothes):
    answer = 1
    dictonary = {x[1]:0 for x in clothes}
    for _,key in clothes:
        dictonary[key]+=1
        
    for key,value in dictonary.items():
        temp = value+1
        answer *= temp
    
    return answer-1