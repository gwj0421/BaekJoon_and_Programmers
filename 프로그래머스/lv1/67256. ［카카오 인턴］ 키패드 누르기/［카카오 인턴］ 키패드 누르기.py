def search(num_list,target):
    ans=[(i,j) for i in range(3) for j in range(4) if num_list[i][j]==target]
    return ans[0]
    

def solution(numbers, hand):
    answer = ''
    pad=[['*',7,4,1],[0,8,5,2],['#',9,6,3]]
    lh=(0,0)
    rh=(2,0)
    
    for i in numbers:
        if i==1 or i==4 or i==7:
            answer+='L'
            lh=search(pad,i)
        elif i==3 or i==6 or i==9:
            answer+='R'
            rh=search(pad,i)
        elif i==2 or i==5 or i==8 or i==0:
            point=search(pad,i)
            leftToPoint=abs(lh[0]-point[0])+abs(lh[1]-point[1])
            rightToPoint=abs(rh[0]-point[0])+abs(rh[1]-point[1])
            if leftToPoint<rightToPoint:
                answer+='L'
                lh=point
            elif leftToPoint>rightToPoint:
                answer+='R'
                rh=point
            else:
                if hand=="right":
                    answer+='R'
                    rh=point
                elif hand=="left":
                    answer+='L'
                    lh=point
                else :
                    pass

    return answer