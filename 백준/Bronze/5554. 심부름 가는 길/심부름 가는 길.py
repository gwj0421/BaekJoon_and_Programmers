sum=0
for i in range(4):
    sum+=int(input())
print('%d\n%d' %(sum//60,sum%60))