def split_quard(arr):
    zero_cnt = 0
    one_cnt = 0
    max_line = len(arr)//2
    leftup = [[y for y in  x[:max_line]] for x in arr[:max_line]]
    leftup_set = set(sum(leftup,[]))
    if len(leftup_set)>1:
        temp1,temp2 = split_quard(leftup)
        zero_cnt += temp1
        one_cnt += temp2
    else:
        _temp = leftup_set.pop()
        if _temp == 1:
            one_cnt += 1
        else:
            zero_cnt+=1
            
    rightup = [[y for y in  x[max_line:]] for x in arr[:max_line]]
    rightup_set = set(sum(rightup,[]))
    if len(rightup_set)>1:
        temp1,temp2 = split_quard(rightup)
        zero_cnt+=temp1
        one_cnt +=temp2
    else:
        _temp = rightup_set.pop()
        if _temp ==1:
            one_cnt+=1
        else:
            zero_cnt+=1
            
    leftdown = [[y for y in  x[:max_line]] for x in arr[max_line:]]
    leftdown_set = set(sum(leftdown,[]))
    if len(leftdown_set)>1:
        temp1,temp2 = split_quard(leftdown)
        zero_cnt+=temp1
        one_cnt +=temp2
    else:
        _temp = leftdown_set.pop()
        if _temp ==1:
            one_cnt+=1
        else:
            zero_cnt+=1
            

            
    rightdown = [[y for y in  x[max_line:]] for x in arr[max_line:]]
    rightdown_set = set(sum(rightdown,[]))
    if len(rightdown_set)>1:
        temp1,temp2 = split_quard(rightdown)
        zero_cnt+=temp1
        one_cnt +=temp2
    else:
        _temp = rightdown_set.pop()
        if _temp ==1:
            one_cnt+=1
        else:
            zero_cnt+=1
    return zero_cnt,one_cnt
#split_quard([[1,1,1,1,1,1,1,1],[0,1,1,1,1,1,1,1],[0,0,0,0,1,1,1,1],[0,1,0,0,1,1,1,1],[0,0,0,0,0,0,1,1],[0,0,0,0,0,0,0,1],[0,0,0,0,1,0,0,1],[0,0,0,0,1,1,1,1]])
def solution(arr):
    if len(set(sum(arr,[])))==1:
        _temp = set(sum(arr,[])).pop()
        if _temp ==1:
            return [0,1]
        else:
            return [1,0]
    answer = split_quard(arr)
    return answer