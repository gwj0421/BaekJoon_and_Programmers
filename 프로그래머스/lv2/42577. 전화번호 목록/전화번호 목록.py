# def solution(phone_book):
#     answer = True
#     phone_book=sorted(phone_book)
#     for n1,n2 in zip(phone_book,phone_book[1:]):
#         if n2.startswith(n1):
#             return False
#     return True
def solution(phone_book):
    answer=True
    hash_map={}
    for phone_number in phone_book:
        hash_map[phone_number]=1
    for phone_number in phone_book:
        temp=""
        for number in phone_number:
            temp+=number
            if temp in hash_map and temp !=phone_number:
                answer=False
    return answer