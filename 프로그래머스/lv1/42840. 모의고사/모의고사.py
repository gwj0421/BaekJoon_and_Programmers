import itertools

def solution(answers):
    cnt=len(answers)
    pattern=[[1,2,3,4,5],[2,1,2,3,2,4,2,5],[3,3,1,1,2,2,4,4,5,5]]
    point=[0,0,0]
    for i in range(len(pattern)):
        for idiot , ans in zip(itertools.cycle(pattern[i]) , answers) :
            if idiot==ans:
                point[i]+=1
    max_value=max(point)
    answers=[i+1 for i, value in enumerate(point) if value == max_value]
    return answers

# def solution(answers):
#     pattern1 = [1,2,3,4,5]
#     pattern2 = [2,1,2,3,2,4,2,5]
#     pattern3 = [3,3,1,1,2,2,4,4,5,5]
#     score = [0, 0, 0]
#     result = []

#     for idx, answer in enumerate(answers):
#         if answer == pattern1[idx%len(pattern1)]:
#             score[0] += 1
#         if answer == pattern2[idx%len(pattern2)]:
#             score[1] += 1
#         if answer == pattern3[idx%len(pattern3)]:
#             score[2] += 1

#     for idx, s in enumerate(score):
#         if s == max(score):
#             result.append(idx+1)

#     return result