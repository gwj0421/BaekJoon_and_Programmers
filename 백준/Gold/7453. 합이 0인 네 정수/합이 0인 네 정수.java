import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        int[] d = new int[n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
            d[i] = Integer.parseInt(st.nextToken());
        }

        int[] combinationAB = new int[n * n];
        int[] combinationCD = new int[n * n];

        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                combinationAB[idx] = a[i] + b[j];
                combinationCD[idx] = c[i] + d[j];
                idx++;
            }
        }
        Arrays.sort(combinationAB);
        Arrays.sort(combinationCD);

        long ans = 0;
        int left = 0;
        int right = n*n - 1;
        int tmp;
        while (left < n*n && right > -1) {
            tmp = combinationAB[left] + combinationCD[right];

            if (tmp < 0) {
                left++;
            } else if (tmp > 0) {
                right--;
            } else {
                long leftCnt = 1;
                long rightCnt = 1;
                while (left + 1 < n*n && combinationAB[left] == combinationAB[left + 1]) {
                    leftCnt++;
                    left++;
                }
                while (right - 1 > -1 && combinationCD[right] == combinationCD[right - 1]) {
                    rightCnt++;
                    right--;
                }
                left++;
                right--;
                ans += leftCnt * rightCnt;
            }
        }
        System.out.println(ans);
    }
}