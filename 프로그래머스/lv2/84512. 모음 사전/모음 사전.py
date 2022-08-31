def solution(word):
    next_dict = {'A':'E','E':'I','I':'O','O':'U','U':''}
    cnt = 1
    word = list(word)
    temp = ['A']
    while word != temp:
        if len(temp) < 5:
            temp.append('A')
        else:
            last_word = temp.pop()
            if last_word == 'U':
                for i in range(len(temp)-1,-1,-1):
                    if temp[i] == 'U':
                        temp.pop()
                    else:
                        temp.append(next_dict[temp.pop()])
                        break
                
            else:
                temp.append(next_dict[last_word])
        
        cnt+=1
        #print(cnt,temp)
        
    return cnt