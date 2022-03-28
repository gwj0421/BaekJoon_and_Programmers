x,y=map(int,input().split())
y-=45
if y<0:
    x-=1
    y+=60
if x<0:
    x=23
print(x,y)
