import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int n = Integer.parseInt(br.readLine());
        int[] sequence = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int m = Integer.parseInt(br.readLine());
        int[][] queries = new int[m][3];
        for (int i = 0; i < m; i++) {
            queries[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        SequenceAndQuery sequenceAndQuery = new SequenceAndQuery(n, sequence, m, queries);
        sequenceAndQuery.applyQueries();
    }

    static class SequenceAndQuery {
        private static final Segment MAX_SEGMENT = new Segment(Integer.MAX_VALUE, Integer.MAX_VALUE);
        private final int n;
        private final int[] sequence;
        private final int m;
        private final int[][] queries;
        private Segment[] segmentTree;

        // 1~5 , 1~3 , 4~5 , 1~2 , 3~3 , 4~4 , 5~5 , 1~1 , 2~2
        // 1   , 3   , 1   , 4   , 3   , 2   , 1   , 5   , 4

        // 5 4 3 2 3 => 3
        // 5 4 3 3 3
        public SequenceAndQuery(int n, int[] sequence, int m, int[][] queries) {
            this.n = n;
            this.sequence = sequence;
            this.m = m;
            this.queries = queries;
            this.segmentTree = new Segment[4 * n];
            initSegmentTree(0, 0, n - 1);
        }

        private Segment initSegmentTree(int idx, int st, int en) {
            if (st == en) {
                return segmentTree[idx] = new Segment(st, sequence[st]);
            }
            int mid = (st + en) / 2;
            return segmentTree[idx] = Segment.choseSmallSegment(
                    initSegmentTree(idx * 2 + 1, st, mid),
                    initSegmentTree(idx * 2 + 2, mid + 1, en)
            );
        }

        public void applyQueries() {
            for (int[] query : queries) {
                if (query[0] == 1) {
                    update(0, 0, n - 1, new Segment(query[1] - 1, query[2]));
                } else {
                    Segment searchTarget = search(0, 0, n - 1, query[1] - 1, query[2] - 1);
                    System.out.println(searchTarget.getIdx() + 1);
                }
            }
        }

        private Segment update(int idx, int st, int en, Segment target) {
            if (target.getIdx() < st || en < target.getIdx()) {
                return segmentTree[idx];
            }
            if (st == en) {
                return segmentTree[idx] = target.copy();
            }
            int mid = (st + en) / 2;
            return segmentTree[idx] = Segment.choseSmallSegment(
                    update(idx * 2 + 1, st, mid, target),
                    update(idx * 2 + 2, mid + 1, en, target)
            );
        }

        private Segment search(int idx, int st, int en, int left, int right) {
            if (right < st || en < left) {
                return MAX_SEGMENT;
            }
            if (left <= st && en <= right) {
                return segmentTree[idx];
            }
            int mid = (st + en) / 2;
            return Segment.choseSmallSegment(
                    search(idx * 2 + 1, st, mid, left, right),
                    search(idx * 2 + 2, mid + 1, en, left, right)
            );
        }
    }

    static class Segment {
        private final int idx;
        private final int val;

        public Segment(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }

        public int getIdx() {
            return idx;
        }

        public int getVal() {
            return val;
        }
        
        public Segment copy() {
            return new Segment(this.idx, this.val);
        }

        public boolean isLessThan(Segment target) {
            if (this.val == target.getVal()) {
                return this.idx < target.getIdx();
            }
            return this.val < target.getVal();
        }

        public static Segment choseSmallSegment(Segment a, Segment b) {
            return a.isLessThan(b) ? a.copy() : b.copy();
        }
    }

}