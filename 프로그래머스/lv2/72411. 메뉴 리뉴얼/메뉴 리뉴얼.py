from itertools import combinations
import collections

# def search_set(orders,menu_num):
#     order_num=len(orders)
#     temp=[]
#     result=[]
#     for i in range(order_num):
#         for j in range(order_num):
#             if i!=j:
#                 combination=combinations(set(orders[i]) & set(orders[j]),menu_num)
#                 for k in combination:
#                     temp.append(k)
                    
#     output=collections.Counter(temp).most_common()
#     if output:
#         maximum = output[0][1]
#         for num in output:
#             if num[1] == maximum:
#                 result.append(''.join(sorted(num[0])))
            
#     return result


# def solution(orders, course):
#     answer = []
#     orders=[list(x) for x in orders]
#     for i in course:
#         answer.append(search_set(orders,i))
#     return sorted(sum(answer,[]))

def solution(orders,course):
    answer=[]
    for i in course:
        order_comb=[]
        for order in orders:
            order_comb+=combinations(sorted(order),i)
        
        most_com=collections.Counter(order_comb).most_common()
        answer+=[k for k,v in most_com if v>1 and v==most_com[0][1]]
    
    return [''.join(x) for x in sorted(answer)]