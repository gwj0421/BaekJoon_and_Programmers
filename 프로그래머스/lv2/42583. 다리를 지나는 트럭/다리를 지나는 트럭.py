def solution(bridge_length, weight, truck_weights):
    time = 0
    visited = []
    visiting = []
    while truck_weights:
        temp_truck = truck_weights.pop(0)
        if sum(visiting)+temp_truck<bridge_length:
            time +=1
            visiting.append(temp_truck)
        else:
            if visiting:
                time+=1
                visited.append(visiting.pop(0))
                truck_weights.insert(0,temp_truck)
            
        if truck_weights:
            for j in truck_weights:
                time+=1
                visited.append(visiting.pop(0))
        
    return time