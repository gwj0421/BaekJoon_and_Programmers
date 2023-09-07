def solve_n_queens(N):
    def backtrack(row):
        nonlocal count
        if row == N:
            count += 1
            return
        for col in range(N):
            if not cols[col] and not diagonal1[row + col] and not diagonal2[row - col]:
                cols[col] = diagonal1[row + col] = diagonal2[row - col] = True
                backtrack(row + 1)
                cols[col] = diagonal1[row + col] = diagonal2[row - col] = False

    count = 0
    cols = [False] * N  # 각 열에 퀸이 있는지 여부
    diagonal1 = [False] * (2 * N - 1)  # 오른쪽 위로 향하는 대각선 방향
    diagonal2 = [False] * (2 * N - 1)  # 왼쪽 위로 향하는 대각선 방향
    backtrack(0)
    return count

# 입력 받기
N = int(input())
result = solve_n_queens(N)
print(result)
