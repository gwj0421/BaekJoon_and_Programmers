def solution(n, arr1, arr2):
    answer = []
    for i,j in zip(arr1,arr2):
        # 비트연산자 사용
        temp=bin(i|j)[2:]
        temp=temp.rjust(n,'0')  # 0으로 자릿수 맞춤
        temp=temp.replace('1','#')  # 1 -> #
        temp=temp.replace('0',' ')  # 0 -> ' '
        answer.append(temp)
    return answer