import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] card = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(card);
        int[] chulsu = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] head = new int[m];
        for (int i = 0; i < m; i++) {
            head[i] = i;
        }
        for (int num : chulsu) {
            int minsuIdx = binarySearch(card, num);
            minsuIdx = find(head, minsuIdx);
            System.out.println(card[minsuIdx]);
            if (minsuIdx + 1 < m) {
                union(head, minsuIdx, minsuIdx + 1);
            }
        }
    }

    public static int binarySearch(int[] card, int val) {
        int start = 0;
        int end = card.length - 1;
        int idx = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (card[mid] > val) {
                end = mid - 1;
                idx = mid;
            } else {
                start = mid + 1;
            }
        }
        return idx;
    }

    public static void union(int[] head, int a, int b) {
        int rootA = find(head, a);
        int rootB = find(head, b);
        head[rootA] = rootB;
    }

    public static int find(int[] head, int target) {
        if (head[target] == target) {
            return target;
        }
        return head[target] = find(head, head[target]);
    }
}

