import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int n = Integer.parseInt(br.readLine());
        int[] sequenceA = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int m = Integer.parseInt(br.readLine());
        int[][] edges = new int[m][];
        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i] = new int[]{Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())};
        }

        int numA = mergeSequence(sequenceA);

        Arrays.sort(sequenceA);
        int sortedNum = mergeSequence(sequenceA);

        Map<Integer, Integer> footprint = new HashMap<>();
        PriorityQueue<Situation> pq = new PriorityQueue<>();
        pq.add(new Situation(numA, 0));
        while (!pq.isEmpty()) {
            Situation now = pq.poll();
            if (now.getCombination() == sortedNum) {
                System.out.println(now.getCost());
                return;
            }
            for (int[] edge : edges) {
                int[] nextSequence = seperateSequence(now.getCombination(), n);
                swapSequence(nextSequence, edge[0], edge[1]);
                Situation next = new Situation(mergeSequence(nextSequence), now.getCost() + edge[2]);
                if (!footprint.containsKey(next.getCombination()) || (footprint.containsKey(next.getCombination()) && next.getCost() < footprint.get(next.getCombination()))) {
                    footprint.put(next.getCombination(), next.getCost());
                    pq.add(next);
                }
            }

        }
        System.out.println(-1);
    }

    public static int mergeSequence(int[] sequence) {
        int result = 0;
        for (int i = 0; i < sequence.length; i++) {
            if (sequence[i] < 10) {
                result = result * 10 + sequence[i];
            } else {
                result = result * 100 + sequence[i];
            }
        }
        return result;
    }

    public static void swapSequence(int[] sequence, int idxA, int idxB) {
        int tmp = sequence[idxA];
        sequence[idxA] = sequence[idxB];
        sequence[idxB] = tmp;
    }
    public static int[] seperateSequence(int sequence, int length) {
        int[] result = new int[length];
        int tmp = sequence;
        int partSize;
        for (int i = length - 1; i > -1; i--) {
            partSize = tmp % 10 > 0 ? 10 : 100;
            result[i] = tmp % partSize;
            tmp /= partSize;
        }
        return result;
    }
    static class Situation implements Comparable<Situation> {
        private final int combination;
        private final int cost;

        public Situation(int combination, int cost) {
            this.combination = combination;
            this.cost = cost;
        }

        public int getCombination() {
            return combination;
        }

        public int getCost() {
            return cost;
        }

        @Override
        public int compareTo(Situation o) {
            return Integer.compare(this.cost, o.getCost());
        }
    }
}



