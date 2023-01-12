def solution():
    string = input().split('-')
    newString = []
    for i in string:
        temp = 0
        for j in i.split('+'):
            temp += int(j.lstrip('0'))
        newString.append(temp)

    if len(newString) > 1:
        ans = newString[0] * 2 - sum(newString)
    else:
        ans = newString[0]
    print(ans)


solution()