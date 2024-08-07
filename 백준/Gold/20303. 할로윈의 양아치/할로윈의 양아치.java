import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        int k = Integer.parseInt(st.nextToken());

        int[] candy = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            candy[i] = Integer.parseInt(st.nextToken());
        }
        int[] head = new int[n + 1];
        int[] groupCnt = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            head[i] = i;
            groupCnt[i]++;
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            union(head, candy, groupCnt, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        int maxValue = 0;
        if (groupCnt[1] < k) {
            maxValue = candy[1];
        }
        int[][] dp = new int[n + 1][k];
        for (int i = groupCnt[1]; i < k; i++) {
            dp[1][i] = candy[1];
        }
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j < k; j++) {
                if (j >= groupCnt[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - groupCnt[i]] + candy[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
                maxValue = Math.max(maxValue, dp[i][j]);
            }
        }
        System.out.println(maxValue);
//        System.out.println(Arrays.toString(head));
//        System.out.println(Arrays.toString(candy));
//        System.out.println(Arrays.toString(groupCnt));
//        System.out.println();
//        for (int[] ints : dp) {
//            System.out.println(Arrays.toString(ints));
//        }
    }

    public static void union(int[] head, int[] candy, int[] groupCnt, int a, int b) {
        int rootA = find(head, a);
        int rootB = find(head, b);
        if (rootA < rootB) {
            head[rootB] = rootA;
            groupCnt[rootA] += groupCnt[rootB];
            groupCnt[rootB] = 0;
            candy[rootA] += candy[rootB];
            candy[rootB] = 0;
        } else if(rootA > rootB){
            head[rootA] = rootB;
            groupCnt[rootB] += groupCnt[rootA];
            groupCnt[rootA] = 0;
            candy[rootB] += candy[rootA];
            candy[rootA] = 0;
        }
    }

    public static int find(int[] head, int target) {
        if (head[target] == target) {
            return target;
        }
        return head[target] = find(head, head[target]);
    }
}
