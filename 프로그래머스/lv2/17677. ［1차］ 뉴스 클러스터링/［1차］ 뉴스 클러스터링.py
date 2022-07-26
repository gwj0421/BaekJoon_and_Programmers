from collections import Counter
import math
def solution(str1, str2):
    answer = 0
    str1_leng = len(str1)
    str2_leng = len(str2)
    str1_com=[]
    str2_com=[]
    for i in range(str1_leng-1):
        temp=str1[i:i+2]
        if temp.isalpha():
            str1_com.append(temp.upper())
    for i in range(str2_leng-1):
        temp=str2[i:i+2]
        if temp.isalpha():
            str2_com.append(temp.upper())
    str1_c = Counter(str1_com)
    str2_c = Counter(str2_com)
    inter = list((str1_c &  str2_c).elements())
    union = list((str1_c |  str2_c).elements())
    
    print(inter)
    print(union)
    if not union and not inter:
        return 65536
    else:
        return math.floor(len(inter) / len(union) * 65536)