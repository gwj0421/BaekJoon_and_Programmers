import java.util.Stack;

class Solution {
    private static int answer = 0;

    public int solution(int[][] board, int[] moves) {
        Stack<Integer> basket = new Stack<>();
        for (int i = 0; i < moves.length; i++) {
            grap(board, moves[i] - 1, basket);
        }
        return answer;
    }

    private void grap(int[][] board, int pos, Stack<Integer> basket) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][pos] != 0) {
                placeDoll(board[i][pos], basket);
                board[i][pos] = 0;
                return;
            }
        }
    }

    private void placeDoll(int dollType, Stack<Integer> basket) {
        if (basket.empty() || basket.peek() != dollType) {
            basket.push(dollType);
        } else {
            answer += 2;
            basket.pop();
        }
    }
}