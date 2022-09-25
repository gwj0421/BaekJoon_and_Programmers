def solution(msg):
    word_dict = ['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z']
    answer = []
    now_word = ''
    while msg:
        for i in range(len(msg),-1,-1):
            now_word = msg[0:i]
            if now_word in word_dict:
                msg = msg[i:]
                if msg:
                    next_word = msg[0]
                    break
                else:
                    next_word = 'XXX'
                    break
        if next_word != 'XXX':
            word_dict.append(now_word+next_word)
        print(now_word,next_word)
        answer.append(word_dict.index(now_word)+1)
    return answer