import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int[] row = new int[n];
        int[] column = new int[m];
        int queryType, parameter1, parameter2;
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            queryType = Integer.parseInt(st.nextToken());
            parameter1 = Integer.parseInt(st.nextToken()) - 1;
            parameter2 = Integer.parseInt(st.nextToken());
            if (queryType == 1) {
                row[parameter1] += parameter2;
            } else if (queryType == 2) {
                column[parameter1] += parameter2;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(row[i] + column[j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

}