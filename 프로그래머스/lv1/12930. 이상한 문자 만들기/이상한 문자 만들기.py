def solution(s):
    s=s.split(' ')
    tlist=[]
    for i in s:
        temp=''
        for index,value in enumerate(i):
            if index%2==0:
                temp+=value.upper()
            else:
                temp+=value.lower()
        tlist.append(temp)
    return ' '.join(tlist)

# def toWeirdCase(s):
#     return " ".join(map(lambda x: "".join([a.lower() if i % 2 else a.upper() for i, a in enumerate(x)]), s.split(" ")))