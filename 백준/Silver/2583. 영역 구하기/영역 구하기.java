import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();
        int K = sc.nextInt();
        boolean[][] board = new boolean[M][N];
        for (int i = 0; i < K; i++) {
            int leftDownX = sc.nextInt();
            int leftDownY = M - sc.nextInt();
            int rightUpX = sc.nextInt();
            int rightUpY = M - sc.nextInt();
            for (int j = rightUpY; j < leftDownY; j++) {
                for (int k = leftDownX; k < rightUpX; k++) {
                    board[j][k] = true;
                }
            }
        }
        int ans = 0;
        int[] controlY = {0, 0, 1, -1};
        int[] controlX = {1, -1, 0, 0};
        List<Integer> block = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!board[i][j]) {
                    board[i][j] = true;
                    int area = 1;
                    LinkedList<int[]> needVisited = new LinkedList<>();
                    needVisited.add(new int[]{i, j});
                    while (!needVisited.isEmpty()) {
                        int[] now = needVisited.removeFirst();
                        for (int k = 0; k < 4; k++) {
                            int nextY = now[0] + controlY[k];
                            int nextX = now[1] + controlX[k];
                            if (-1 < nextY && nextY < M && -1 < nextX && nextX < N) {
                                if (!board[nextY][nextX]) {
                                    area += 1;
                                    board[nextY][nextX] = true;
                                    needVisited.add(new int[]{nextY, nextX});
                                }
                            }
                        }
                    }
                    block.add(area);
                }
            }
        }
        Collections.sort(block);
        
        System.out.println(block.size());
        System.out.println(block.stream().map(Object::toString).collect(Collectors.joining(" ")));
    }
}

