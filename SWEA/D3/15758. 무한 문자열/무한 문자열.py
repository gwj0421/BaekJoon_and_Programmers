def gcd(a,b):
    while a%b != 0:
        a,b = b,a%b
    return b

def lcm(a,b):
    return a*b//gcd(a,b)

T = int(input())
for test_case in range(1, T + 1):
    big,small = input().split()
    if len(small) > len(big):
        temp = big
        big = small
        small = temp
    temp = lcm(len(big),len(small))
    temp1 = temp//len(big)
    temp2 = temp//len(small)

    if big * temp1 == small * temp2:
        print("#{} yes".format(test_case))
    else:
        print("#{} no".format(test_case))