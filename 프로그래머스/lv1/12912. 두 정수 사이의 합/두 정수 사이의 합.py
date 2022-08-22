# def solution(a, b):
#     if a<b:
#         return sum(range(a,b+1))
#     else:
#         return sum(range(b,a+1))
    
def solution(a,b):
    # 등차수열 공식, 첫째항a 마지막항i n항
    # n*(a+i)//2
    return (abs(a-b)+1)*(a+b)//2