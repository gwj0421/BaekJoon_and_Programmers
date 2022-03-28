for i in range(int(input())):
    inp=int(input())
    d1=[1,0,1]
    d2=[0,1,1]
    if inp>=len(d1):
        for j in range(3,inp+1):
            d1.append(d1[-1]+d1[-2])
            d2.append(d2[-1]+d2[-2])
        print(d1[-1],d2[-1])
    else :
        print(d1[inp],d2[inp])