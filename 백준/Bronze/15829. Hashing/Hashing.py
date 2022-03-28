#15829
l=int(input())
line=list(input())
result=0
for i in range(l):
    line[i]=(ord(line[i])-96)*31**i

print(sum(line)%1234567891)
    
