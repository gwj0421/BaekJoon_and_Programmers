import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Jewel {
    private final int weight;
    private final int price;

    public Jewel(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public int getPrice() {
        return price;
    }

    public static void sort(Jewel[] jewels) {
        Arrays.sort(jewels, (o1, o2) -> {
            if (o1.getWeight() == o2.getWeight()) {
                return o2.getPrice() - o1.getPrice();
            }
            return o1.getWeight() - o2.getWeight();
        });
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Jewel[] jewels = new Jewel[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int mi = Integer.parseInt(st.nextToken());
            int vi = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(mi, vi);
        }
        Jewel.sort(jewels);

        int[] pocketSize = new int[k];
        for (int i = 0; i < k; i++) {
            pocketSize[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(pocketSize);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        long ans = 0;
        int j = 0;
        for (int i = 0; i < k; i++) {
            while (j < n && jewels[j].getWeight() <= pocketSize[i]) {
                pq.offer(jewels[j++].getPrice());
            }
            if (!pq.isEmpty()) {
                ans += pq.poll();
            }
        }
        System.out.println(ans);
    }
}