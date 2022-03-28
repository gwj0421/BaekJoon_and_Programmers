t=int(input())
for i in range(t):
    x1,y1,x2,y2=map(int,input().split())
    n=int(input())
    cnt=0
    for j in range(n):
        dx,dy,distance=map(int,input().split())
        temp1=((dx-x1)**2+(dy-y1)**2)**0.5
        temp2=((dx-x2)**2+(dy-y2)**2)**0.5
        if (temp1<distance and temp2>distance) or (temp1>distance and temp2<distance):
            cnt+=1
    print(cnt)