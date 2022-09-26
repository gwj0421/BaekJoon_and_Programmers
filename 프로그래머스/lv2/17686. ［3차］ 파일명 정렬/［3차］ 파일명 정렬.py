import re
def solution(files):
    answer = []
    file_dict = {}
    for i in files:
        head_patten = re.search("[^0-9]+",i)
        number_patten = re.search("[0-9]+",i)
        head = head_patten.group()
        number = number_patten.group()
        print(head,number)
        if file_dict.get(head.lower()) == None:
            file_dict[head.lower()] = [[int(number),i]]
        else:
            file_dict[head.lower()].append([int(number),i])

    file_dict = dict(sorted(file_dict.items()))
    for key,value in file_dict.items():
        for i in sorted(value, key=lambda x : x[0]):
            answer.append(i[1])

    return answer