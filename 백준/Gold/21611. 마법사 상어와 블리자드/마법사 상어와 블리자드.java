import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Magic {
    private static final int[] MOVE_Y = {0, 1, 0, -1};
    private static final int[] MOVE_X = {-1, 0, 1, 0};
    private final int n;
    private int[][] board;
    private int score = 0;

    public Magic(int n, int[][] board) {
        this.n = n;
        this.board = board;
    }

    public int[][] getBoard() {
        return board;
    }

    public int getN() {
        return n;
    }

    public int getScore() {
        return score;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static void activate(int[][] board, int n, int[][] spell) {
        Magic magic = new Magic(n, board);
        for (int i = 0; i < spell.length; i++) {
            magic.blizzard(spell[i][0], spell[i][1]);
//            System.out.println("blizzard");
//            for (int[] ints : magic.getBoard()) {
//                System.out.println(Arrays.toString(ints));
//            }
            Queue<Integer> flat = magic.convertFlat();
//            System.out.println("flat");
//            System.out.println(flat);
            Stack<int[]> explosionResult = magic.explosion(flat);
//            System.out.println("explosion");
//            for (int[] ints : explosionResult) {
//                System.out.println(Arrays.toString(ints));
//            }

            Deque<Integer> groupingResult = magic.grouping(explosionResult);
//            System.out.println("grouping");
//            System.out.println(groupingResult);
            magic.convert2d(groupingResult);
//            System.out.println("result");
//            for (int[] ints : magic.getBoard()) {
//                System.out.println(Arrays.toString(ints));
//            }
        }
        System.out.println(magic.getScore());
    }

    private void blizzard(int di, int si) {
        Map<Integer, Integer> dirConv = Map.of(1, 3, 2, 1, 3, 0, 4, 2);
        int sy = getN() / 2;
        int sx = getN() / 2;
        di = dirConv.get(di);
        for (int i = 0; i < si; i++) {
            sy += MOVE_Y[di];
            sx += MOVE_X[di];
            getBoard()[sy][sx] = -1;
        }
    }

    private Stack<int[]> explosion(Queue<Integer> flat) {
        Stack<int[]> stack;
        boolean isExp;
        while (true) {
            stack = new Stack<>();
            isExp = false;
            while (!flat.isEmpty()) {
                int ballType = flat.poll();
                if (ballType == -1 || ballType == 0) {
                    continue;
                }
                if (stack.isEmpty() || stack.peek()[1] != ballType) {
                    stack.push(new int[]{1, ballType});
                } else {
                    stack.peek()[0]++;
                }
            }
            Stack<int[]> nextStack = new Stack<>();
            for (int[] now : stack) {
                if (now[0] < 4) {
                    nextStack.push(now);
                } else {
                    if (now[1] < 4) {
                        isExp = true;
                        setScore(getScore() + now[0] * now[1]);
                    }
                }
            }

            if (!isExp) {
                return nextStack;
            }
            for (int[] ns : nextStack) {
                for (int i = 0; i < ns[0]; i++) {
                    flat.add(ns[1]);
                }
            }
        }
    }

    private Deque<Integer> grouping(Stack<int[]> stack) {
        Deque<Integer> result = new LinkedList<>();
        while (!stack.isEmpty()) {
            int[] group = stack.pop();
            result.addFirst(group[1]);
            result.addFirst(group[0]);
        }
        return result;
    }

    private Queue<Integer> convertFlat() {
        Queue<Integer> flat = new LinkedList<>();
        int cnt = 1;
        int d = 0;
        int sy = getN() / 2;
        int sx = getN() / 2;

        while (true) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < cnt; j++) {
                    sy += MOVE_Y[d];
                    sx += MOVE_X[d];
                    if (sx < 0 || sy < 0) {
                        return flat;
                    }
                    if (getBoard()[sy][sx] != -1) {
                        flat.add(getBoard()[sy][sx]);
                    }
                }
                d = (d + 1) % 4;
            }
            cnt++;
        }
    }

    private void convert2d(Deque<Integer> flat) {
        int[][] nextBoard = new int[getN()][getN()];
        int cnt = 1;
        int d = 0;
        int sy = getN() / 2;
        int sx = getN() / 2;

        while (true) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < cnt; j++) {
                    sy += MOVE_Y[d];
                    sx += MOVE_X[d];
                    if (flat.isEmpty() || sx < 0 || sy < 0) {
                        setBoard(nextBoard);
                        return;
                    }
                    nextBoard[sy][sx] = flat.pollFirst();
                }
                d = (d + 1) % 4;
            }
            cnt++;
        }
    }
}

public class Main {
    //7 1
    //48 47 46 45 44 43 42
    //25 24 23 22 21 20 41
    //26 9 8 7 6 19 40
    //27 10 1 0 5 18 39
    //28 11 2 3 4 17 38
    //29 12 13 14 15 16 37
    //30 31 32 33 34 35 36
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] board = new int[n][n];
        int[][] spell = new int[m][];
        for (int i = 0; i < n; i++) {
            int j = 0;
            for (String num : br.readLine().split(" ")) {
                board[i][j] = Integer.parseInt(num);
                j++;
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int di = Integer.parseInt(st.nextToken());
            int si = Integer.parseInt(st.nextToken());
            spell[i] = new int[]{di, si};
        }
        Magic.activate(board, n, spell);
    }
}