import re
from itertools import permutations
def solution(user_id, banned_id):
    answer = []
    user_id_permutation = list(permutations(user_id,len(banned_id)))
    for _ids in user_id_permutation:
        cnt = 0
        for i in range(len(banned_id)):
            p = re.compile('^{}$'.format(banned_id[i].replace('*','.')))
            if p.match(_ids[i]):
                cnt += 1
        if cnt == len(banned_id):
            answer.append(sorted(_ids))
    return len(set(map(tuple,answer)))