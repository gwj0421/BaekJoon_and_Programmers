# def solution(num):
#     cnt=0
#     while(num!=1):
#         cnt+=1
#         if num%2==0 and cnt<500:
#             num/=2
#         elif num%2==1 and cnt<500 :
#             num=num*3+1
#         else :
#             return -1            
#     return cnt

def solution(num):
    if num==1:
        return 0
    cnt = 0
    for _ in range(500):
        num=num/2 if num%2==0 else num*3+1
        cnt+=1
        if num==1:
            break
    if cnt ==500:
        return -1
    else:
        return cnt