import sys
n=int(sys.stdin.readline())
line=[0]*100001
for i in range(n):
  line[int(sys.stdin.readline())]+=1
for i in range(100001):
  if line[i]!=0:
    for j in range(line[i]):
      print(i)