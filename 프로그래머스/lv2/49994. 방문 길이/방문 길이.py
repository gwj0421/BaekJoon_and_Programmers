def solution(dirs):
    answer = 0
    control = {'U':[0,1],'D':[0,-1],'R':[1,0],'L':[-1,0]}
    x,y = 0,0
    visited = []
    for i in dirs:
        next_x,next_y =x+control[i][0],y+control[i][1]
        if -6<next_x<6 and -6<next_y<6:
            start = ''.join(map(str,[x,y]))
            end = ''.join(map(str,[next_x,next_y]))
            if [start,end] in visited or [end,start] in visited:
                pass
            else:
                answer+=1
                visited.append([start,end])
            x,y = next_x,next_y
         
    return answer