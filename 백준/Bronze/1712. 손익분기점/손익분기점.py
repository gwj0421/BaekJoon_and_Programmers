values = input()
values = values.split()
a = int(values[0])
b = int(values[1])
c = int(values[2])
if b < c:
    print(a//(c-b)+1)
else :
    print(-1)