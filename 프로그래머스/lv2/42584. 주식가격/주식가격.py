def solution(prices):
    answer = [0]*len(prices)
    p = 0
    
    for i in range(len(prices)):
        for j in range(i+1,len(prices)):
            if prices[i]<=prices[j]:
                answer[i]+=1
            else:
                answer[i]+=1
                break
            
    return answer