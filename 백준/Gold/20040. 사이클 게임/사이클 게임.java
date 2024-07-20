import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] head = new int[n];
        for (int i = 0; i < n; i++) {
            head[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            if (union(head, p1, p2)) {
                System.out.println(i + 1);
                return;
            }
        }
        System.out.println(0);
    }

    public static boolean union(int[] head,int a, int b) {
        int rootA = find(head, a);
        int rootB = find(head, b);
        if (rootA == rootB) {
            return true;
        }
        if (rootA < rootB) {
            head[rootB] = rootA;
        } else {
            head[rootA] = rootB;
        }
        return false;
    }

    public static int find(int[] head, int point) {
        if (head[point] == point) {
            return point;
        }
        return head[point] = find(head, head[point]);
    }

}
