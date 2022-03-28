#1181
num=int(input())
temp=[]
for i in range(num):
    temp.append(input())
new_temp=list(set(temp))
new_temp.sort()
new_temp.sort(key=lambda x:len(x))
print('\n'.join(new_temp))
