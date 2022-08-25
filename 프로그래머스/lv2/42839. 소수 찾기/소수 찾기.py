from itertools import permutations

def prime_table(max_num=9999999):
    table = [False]*2 + [True]*(max_num-1)
    for i in range(2,int(max_num**0.5)+1):
        if table[i] == True:
            for j in range(i*2,max_num,i):
                table[j] = False
    return table

def solution(numbers):
    answer = 0
    length = len(numbers)
    temp  = []
    table = prime_table()
    for i in range(1,length+1):
        perm = list(permutations(numbers,i))
        perm = [''.join(x) for x in perm]
        perm = list(map(int,perm))
        temp.extend(perm)

    for j in set(temp):
        if table[j]:
            answer+=1
                
    return answer
solution('011')