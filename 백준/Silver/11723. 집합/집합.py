import sys


def controller(m):
    emptySetS = set()
    for _ in range(m):
        allCommand = input().rstrip('\n')
        if allCommand == 'all':
            emptySetS = {'1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20'}
        elif allCommand == 'empty':
            emptySetS = set()
        else:
            command, num = allCommand.split()
            if command == 'add':
                emptySetS.add(num)
            elif command == 'remove':
                emptySetS.discard(num)
            elif command == 'check':
                if num in emptySetS:
                    print(1)
                else:
                    print(0)
            elif command == 'toggle':
                if num in emptySetS:
                    emptySetS.discard(num)
                else:
                    emptySetS.add(num)


def solution():
    m = int(input().rstrip('\n'))
    controller(m)


input = sys.stdin.readline
solution()