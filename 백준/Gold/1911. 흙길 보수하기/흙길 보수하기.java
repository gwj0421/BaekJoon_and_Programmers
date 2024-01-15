import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Pool {
    private int start;
    private int end;
    private final int l;

    public Pool(int start, int end, int l) {
        this.start = start;
        this.end = end;
        this.l = l;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public int getL() {
        return l;
    }

    public int getSize() {
        return getEnd() - getStart();
    }

    public static void sort(Pool[] pools) {
        Arrays.sort(pools, (o1, o2) -> o1.getStart() - o2.getStart());
    }

    public int getPlankCnt() {
        if (getSize() % getL() == 0) {
            return getSize() / getL();
        }
        return getSize() / getL() + 1;
    }

    public int getExtraIdx() {
        return getStart() + getPlankCnt() * getL()-1;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        Pool[] pools = new Pool[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int ps = Integer.parseInt(st.nextToken());
            int pe = Integer.parseInt(st.nextToken());
            pools[i] = new Pool(ps, pe, l);
        }
        Pool.sort(pools);

        int cnt = pools[0].getPlankCnt();
        int idx = pools[0].getExtraIdx();
//        System.out.println(cnt + " " + idx);
        for (int i = 1; i < n; i++) {
//            System.out.println();
            if (idx < pools[i].getStart()) {
                cnt += pools[i].getPlankCnt();
                idx = pools[i].getExtraIdx();
            } else if (idx < pools[i].getEnd()) {
                pools[i].setStart(++idx);
                cnt += pools[i].getPlankCnt();
                idx = pools[i].getExtraIdx();
            }
//            System.out.println(pools[i].getStart() + " " + pools[i].getEnd());
//            System.out.println(cnt + " " + idx);
        }
        System.out.println(cnt);
    }
}