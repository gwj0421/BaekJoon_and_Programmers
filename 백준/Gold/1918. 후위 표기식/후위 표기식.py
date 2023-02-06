def infixToPostfix(infix):
    operations = []
    ans = ''
    for i in infix:
        if i == '+' or i == '-':
            while operations and operations[-1] != '(':
                ans += operations.pop()
            operations.append(i)

        elif i == '*' or i == '/':
            while operations and (operations[-1] == '*' or operations[-1] == '/'):
                ans += operations.pop()
            operations.append(i)

        elif i == '(':
            operations.append(i)
        elif i == ')':
            while operations and operations[-1] != '(':
                ans += operations.pop()
            operations.pop()
        else:
            ans += i
    while operations:
        ans += operations.pop()
    return ans


def solution():
    before = input()

    print(infixToPostfix(before))


solution()