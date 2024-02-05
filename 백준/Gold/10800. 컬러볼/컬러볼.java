import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Ball {
    private final int idx;
    private final int color;
    private final int size;

    public Ball(int idx, int color, int size) {
        this.idx = idx;
        this.color = color;
        this.size = size;
    }

    public int getIdx() {
        return idx;
    }

    public int getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Ball[] ballInfo = new Ball[n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int ci = Integer.parseInt(st.nextToken());
            int si = Integer.parseInt(st.nextToken());
            ballInfo[i] = new Ball(i, ci, si);
        }
        Arrays.sort(ballInfo, (o1, o2) -> o1.getSize() - o2.getSize());

        int[] result = new int[n];
        int[] colors = new int[n + 1];
        int total = 0;
        for (int i = 0, j = 0; i < n; i++) {
            Ball ball = ballInfo[i];
            while (ball.getSize() > ballInfo[j].getSize()) {
                total += ballInfo[j].getSize();
                colors[ballInfo[j].getColor()] += ballInfo[j].getSize();
                j++;
            }
            result[ball.getIdx()] = total - colors[ball.getColor()];
        }
        for (int i : result) {
            System.out.println(i);
        }
    }
}