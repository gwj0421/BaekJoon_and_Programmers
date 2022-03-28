#2108
from collections import Counter
def arithmeticMean(alist):
    return sum(alist)/len(alist)
def mid(alist):
    alist.sort()
    return alist[len(alist)//2]
def manyCount(alist):
    temp=Counter(alist).most_common()
    if len(alist)>1:
        if temp[0][1]==temp[1][1]:
            return temp[1][0]
        else:
            return temp[0][0]
    else : 
        return temp[0][0]
def NumberRange(alist):
    return max(alist)-min(alist)

n=int(input())
numberlist=[]
for i in range(n):
    numberlist.append(int(input()))
print(round(arithmeticMean(numberlist)))
print(mid(numberlist))
print(manyCount(numberlist))
print(NumberRange(numberlist))