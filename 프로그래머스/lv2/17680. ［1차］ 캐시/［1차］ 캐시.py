from collections import deque
def solution(cacheSize, cities):
    answer = 0
    cache = deque([])
    cities = cities[::-1]
    if cacheSize == 0:
        return 5*len(cities)
    
    while cities:
        temp = cities.pop().lower()
        if temp in cache:
            cache.remove(temp)
            cache.append(temp)
            answer+=1
        else:
            if len(cache)<cacheSize:
                cache.append(temp)
            else:
                cache.popleft()
                cache.append(temp)
            answer+=5
            
    return answer