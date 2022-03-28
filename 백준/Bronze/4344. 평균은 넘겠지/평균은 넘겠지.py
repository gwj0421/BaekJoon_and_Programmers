number=int(input())
for i in range(number):
    count=0
    num=input().split()
    scores=list(map(float,num[1:]))
    average=sum(scores)/len(scores)
    for temp in scores:
        if temp>average:
            count+=1
    print('{:.3f}%'.format(count/len(scores)*100))
