import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final boolean DEBUG_MODE = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br;
        if (DEBUG_MODE) {
            br = new BufferedReader(new FileReader("./input.txt"));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        int n = Integer.parseInt(br.readLine());
        int[] required = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        Map<Integer, List<Integer>> tailCntMap = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            required[i] = Integer.parseInt(st.nextToken());
            tailCntMap.computeIfAbsent(required[i], k -> new ArrayList<>()).add(i);
        }

        Solution solution = new Solution(n, required, tailCntMap);
        solution.solve();
    }

    static class Solution {
        private final int n;
        private final int[] required;
        private final Map<Integer, List<Integer>> tailInfos;

        private long ans;

        public Solution(int n, int[] required, Map<Integer, List<Integer>> tailInfos) {
            this.n = n;
            this.required = required;
            this.tailInfos = tailInfos;
            this.ans = 0;
        }

        /**
         * 팀 성립 조건
         * 1. (1cycle 3개)
         * 2. (꼬리 1개 달린 1cycle 1개) + (1cycle 1개)
         * 3. (병렬 2개 꼬리 달린 1cycle 1개)
         * 4. (직렬 2개 꼬리 달린 1cycle 1개)
         * 5. (2cycle 1개) + (1cycle 1개)
         * 6. (꼬리 1개 달린 2cycle 1개)
         * 7. (3cycle 1개)
         * <p>
         * 그럼으로 필요한 변수와 사용 조건
         * - 전체 1cycle들의 총 개수 => 1번, 2번, 5번 조건
         * - 전체 1cycle들의 꼬리 개수 => 2번 , 3번 조건
         * - 전체 1cycle들의 꼬리의 꼬리의 개수 => 4번 조건
         * - 전체 2cycle들의 총 개수 => 5번 조건
         * - 전체 2cycle들의 꼬리 개수 => 6번 조건
         * - 전체 3cycle들의 총 개수 => 7번 조건
         */

        public void solve() {
            SoloCycleCondition soloCycleCondition = new SoloCycleCondition(required, tailInfos);
            DuoCycleCondition duoCycleCondition = new DuoCycleCondition(required, tailInfos);
            TrioCycleCondition trioCycleCondition = new TrioCycleCondition(required, tailInfos);
            boolean[] visit = new boolean[n + 1];
            for (int i = 1; i <= n; i++) {
                if (!visit[i]) {
                    if (soloCycleCondition.canActivate(i)) {
                        soloCycleCondition.processVisit(visit, i, 1);
                        soloCycleCondition.processTail(i);
                        soloCycleCondition.processParallel(i);
                        soloCycleCondition.processSerial(i);
                    } else if (duoCycleCondition.canActivate(i)) {
                        duoCycleCondition.processVisit(visit, i, 2);
                        duoCycleCondition.processTail(i);
                    } else if (trioCycleCondition.canActivate(i)) {
                        trioCycleCondition.processVisit(visit, i, 3);
                    }
                }
            }

            // 1번
            ans = NumberUtils.plusValue(ans, NumberUtils.nC3(soloCycleCondition.getTotalCnt()));
            // 2번
            ans = NumberUtils.plusValue(ans, NumberUtils.multiValue(soloCycleCondition.getTotalCnt() - 1, soloCycleCondition.getTailCnt()));
            // 3번
            ans = NumberUtils.plusValue(ans, soloCycleCondition.getParallelTailCnt());
            // 4번
            ans = NumberUtils.plusValue(ans, soloCycleCondition.getSerialTailCnt());
            // 5번
            ans = NumberUtils.plusValue(ans, NumberUtils.multiValue(duoCycleCondition.getTotalCnt(), soloCycleCondition.getTotalCnt()));
            // 6번
            ans = NumberUtils.plusValue(ans, duoCycleCondition.getTailCnt());
            // 7번
            ans = NumberUtils.plusValue(ans, trioCycleCondition.getTotalCnt());

            System.out.println(ans);
        }
    }
}

class NumberUtils {
    private static final int MOD = 1_000_000_007;

    public static long nC2(long value) {
        if (value < 2) {
            return 0;
        }
        return value * (value - 1) / 2;
    }

    public static long nC3(long value) {
        if (value < 3) {
            return 0;
        }
        return value * (value - 1) * (value - 2) / 6;
    }

    public static long plusValue(long target, long value) {
        return (target + value % MOD) % MOD;
    }

    public static long multiValue(long target, long value) {
        return target * value % MOD;
    }
}

class SoloCycleCondition extends Condition {
    private long tailCnt;
    private long parallelTailCnt;
    private long serialTailCnt;

    public SoloCycleCondition(int[] heads, Map<Integer, List<Integer>> tailInfos) {
        super(heads, tailInfos);
        this.tailCnt = 0;
        this.parallelTailCnt = 0;
        this.serialTailCnt = 0;
    }

    public long getTailCnt() {
        return tailCnt;
    }

    public long getParallelTailCnt() {
        return parallelTailCnt;
    }

    public long getSerialTailCnt() {
        return serialTailCnt;
    }

    @Override
    public boolean canActivate(int start) {
        return start == getHead(start, 1);
    }

    public void processTail(int start) {
        this.tailCnt = NumberUtils.plusValue(this.tailCnt, getTail(start).size() - 1);
    }

    public void processParallel(int start) {
        this.parallelTailCnt = NumberUtils.plusValue(this.parallelTailCnt, NumberUtils.nC2(getTail(start).size() - 1));
    }

    public void processSerial(int start) {
        for (int firstTail : getTail(start)) {
            if (firstTail != start) {
                this.serialTailCnt = NumberUtils.plusValue(this.serialTailCnt, getTail(firstTail).size());
            }
        }
    }
}

class DuoCycleCondition extends Condition {
    private long tailCnt;

    public DuoCycleCondition(int[] heads, Map<Integer, List<Integer>> tailInfos) {
        super(heads, tailInfos);
        this.tailCnt = 0;
    }

    public long getTailCnt() {
        return tailCnt;
    }

    @Override
    public boolean canActivate(int start) {
        return start == getHead(start, 2);
    }

    public void processTail(int start) {
        this.tailCnt = NumberUtils.plusValue(this.tailCnt, getTail(start).size() + getTail(getHead(start, 1)).size() - 2);
    }
}

class TrioCycleCondition extends Condition {
    public TrioCycleCondition(int[] heads, Map<Integer, List<Integer>> tailInfos) {
        super(heads, tailInfos);
    }

    @Override
    public boolean canActivate(int start) {
        return start == getHead(start, 3);
    }
}

abstract class Condition {
    private final int[] heads;
    private final Map<Integer, List<Integer>> tailInfos;
    private long totalCnt;

    public Condition(int[] heads, Map<Integer, List<Integer>> tailInfos) {
        this.heads = heads;
        this.tailInfos = tailInfos;
        this.totalCnt = 0;
    }

    public long getTotalCnt() {
        return totalCnt;
    }

    abstract public boolean canActivate(int start);

    public void processVisit(boolean[] visit, int start, int depth) {
        for (int i = 0; i < depth; i++) {
            visit[start] = true;
            start = getHead(start, 1);
        }
        count();
    }

    private void count() {
        this.totalCnt = NumberUtils.plusValue(this.totalCnt, 1);
    }

    public int getHead(int node, int depth) {
        for (int i = 0; i < depth; i++) {
            node = heads[node];
        }
        return node;
    }

    public List<Integer> getTail(int start) {
        return tailInfos.computeIfAbsent(start, k -> List.of());
    }
}