import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Main {
    private static final boolean DEBUG_MODE = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br;
        if (DEBUG_MODE) {
            br = new BufferedReader(new FileReader("./input.txt"));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        int n = Integer.parseInt(br.readLine());
        int[] nodes = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


        DegreeMaker degreeMaker = new DegreeMaker(n, nodes);
        degreeMaker.printResult();

    }

}

class DegreeMaker {
    private final int n;
    private final PriorityQueue<Node> pq;

    public DegreeMaker(int n, int[] nodes) {
        this.n = n;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            if (nodes[i] != 0) {
                pq.add(new Node(i, nodes[i]));
            }
        }
        this.pq = pq;
    }

    public void printResult() {
        List<Node> tmp = new ArrayList<>();
        int[][] result = new int[n][n];
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (now.getRemainDegreeCnt() > pq.size()) {
                System.out.println(-1);
                return;
            }
            
            tmp.clear();
            for (int i = 0; i < now.getRemainDegreeCnt(); i++) {
                Node next = pq.poll();
                result[now.getIdx()][next.getIdx()] =
                        result[next.getIdx()][now.getIdx()] = 1;
                next.connect();
                if (next.getRemainDegreeCnt() > 0) {
                    tmp.add(next);
                }
            }
            pq.addAll(tmp);
        }
//        for (int[] ints : result) {
//            System.out.println(Arrays.stream(ints).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
//        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(result[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

}

class Node implements Comparable<Node> {
    private final int idx;
    private int remainDegreeCnt;

    public Node(int idx, int remainDegreeCnt) {
        this.idx = idx;
        this.remainDegreeCnt = remainDegreeCnt;
    }

    public int getIdx() {
        return idx;
    }

    public int getRemainDegreeCnt() {
        return remainDegreeCnt;
    }

    public void connect() {
        this.remainDegreeCnt--;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(o.getRemainDegreeCnt(), getRemainDegreeCnt());
    }
}