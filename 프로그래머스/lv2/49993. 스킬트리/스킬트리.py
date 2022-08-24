def solution(skill, skill_trees):
    answer = 0
    for i in skill_trees:
        available = list(skill)
        for j in i:
            if j in available:
                stand = available.pop(0)
                if j !=stand:
                    answer+=1
                    break
                    
                
    return len(skill_trees)-answer