# my solution - time over
# def solution(s):
#     leng=len(s)
#     cp=[False for _ in range(leng)]
#     for i in range(leng-1):
#         if s[i]==s[i+1]:
#             cp[i],cp[i+1]=True,True
#             if i!=0 and i!=leng-2:
#                 for j in range(1,min(i+1,leng-1-i)):
#                     if s[i-j]==s[i+1+j]:
#                         cp[i-j],cp[i+1+j]=True,True
#             i+=1
#     if False not in cp:
#         answer=1
#     else:
#         answer=0
#     return answer

# my solution 2
def solution(s):
    stack=[]
    for i in s:
        if len(stack)==0:
            stack.append(i)
        else:
            if stack[-1]==i:
                stack.pop()
            else:
                stack.append(i)
    if stack:
        answer=0
    else:
        answer=1
    return answer