num1,num2=map(int,input().split())
result=num1+num2
result=list(map(int,str(result)))
temp=0
for i in reversed(range(len(result))):
    if i==0 and result[i]>=2:
        result[0]-=2
        result.insert(0,1)
        
    elif result[i]>=2:
        result[i-1]+=1
        result[i]-=2
for i in result:
    print(i,end='')