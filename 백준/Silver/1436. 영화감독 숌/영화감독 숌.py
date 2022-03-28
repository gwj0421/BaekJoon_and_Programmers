#1436
temp=int(input())
count=0
number=666
while True:
    if '666' in str(number):
        count+=1
    if count==temp:
        print(number)
        break
    number+=1
