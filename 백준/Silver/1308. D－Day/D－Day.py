def dayCal(day):
    end=[0,31,28,31,30,31,30,31,31,30,31,30,31]
    endY=[0,31,29,31,30,31,30,31,31,30,31,30,31]
    sumend=sum(end)
    sumendY=sum(endY)
    sumday=0
    for i in range(1,day[0]):
        if i%4==0:
            if i%100==0:
                if i%400==0:
                    sumday+=sumendY
                else :
                    sumday+=sumend
            else:
                sumday+=sumendY
        else :
            sumday+=sumend
    for i in range(1,day[1]):
        if day[0]%4==0:
            if day[0]%100==0:
                if day[0]%400==0:
                    sumday+=endY[i]
                else :
                    sumday+=end[i]
            else:
                sumday+=endY[i]
        else :
            sumday+=end[i]

    sumday+=day[2]
    return sumday

nowday=list(map(int,input().split()))
endday=list(map(int,input().split()))

if (endday[0]==nowday[0]+1000 and endday[1]==nowday[1] and endday[2]==nowday[2]) or (endday[0]>nowday[0]+1000 ) or (endday[0]==nowday[0]+1000 and endday[1]>nowday[1]) or (endday[0]==nowday[0]+1000 and endday[1]==nowday[1] and endday[2]>nowday[2]):
    print('gg')
else :
    Dday=dayCal(endday)-dayCal(nowday)
    print('D-{}'.format(Dday))
