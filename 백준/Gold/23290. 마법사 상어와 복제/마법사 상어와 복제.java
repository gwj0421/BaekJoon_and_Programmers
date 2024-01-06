import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


class Pool {
    private int smell;
    private List<Integer> fishes;

    public Pool() {
        this.smell = 0;
        this.fishes = new ArrayList<>();
    }

    public Pool(int smell) {
        this.smell = smell;
        this.fishes = new ArrayList<>();
    }

    public Pool(Pool pool) {
        this.smell = pool.getSmell();
        this.fishes = pool.getFishes();
    }

    public int getSmell() {
        return smell;
    }

    public void setSmell(int smell) {
        this.smell = smell;
    }

    public List<Integer> getFishes() {
        return fishes;
    }

    @Override
    public String toString() {
        return "{smell=" + smell +
                ", fishes=" + fishes +
                '}';
    }

    public static Pool[][] makeEmptyPools(int size) {
        Pool[][] emptyPools = new Pool[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                emptyPools[i][j] = new Pool();
            }
        }
        return emptyPools;
    }

    public static Pool[][] makeEmptyPoolsRemainSmell(int size, Pool[][] pools) {
        Pool[][] emptyPoolsRemainSmell = new Pool[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                emptyPoolsRemainSmell[i][j] = new Pool(pools[i][j].getSmell());
            }
        }
        return emptyPoolsRemainSmell;
    }

    public static Pool[][] copyPools(int size, Pool[][] original) {
        Pool[][] copy = new Pool[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                copy[i][j] = new Pool(original[i][j]);
            }
        }
        return copy;
    }

    public static void cleanUp(int size, Pool[][] pools) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                pools[i][j].disappearsSmell();
            }
        }
    }

    public static void copyFishes(int size, Pool[][] original, Pool[][] target) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                target[i][j].addFishes(original[i][j].getFishes());
            }
        }
    }

    public static int getFishCntInPools(int size, Pool[][] pools) {
        int cnt = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cnt += pools[i][j].getFishes().size();
            }
        }
        return cnt;
    }

    public void addFish(int d) {
        this.fishes.add(d);
    }

    public void addFishes(List<Integer> fishes) {
        this.fishes.addAll(fishes);
    }

    public void removeFishes() {
        this.fishes = new ArrayList<>();
    }

    public void disappearsSmell() {
        if (this.smell > 0) {
            this.smell--;
        }
    }

    public void eatFishes() {
        if (!getFishes().isEmpty()) {
            removeFishes();
            setSmell(3);
        }
    }
}

class Dfs {
    public static final int SHARK_MOVE_CNT = 3;
    public static final int[] SHARK_MOVE_Y = {-1, 0, 1, 0};
    public static final int[] SHARK_MOVE_X = {0, -1, 0, 1};
    private int maxEatCnt;
    private int[] maxEatRoute;
    private Pool[][] pools;

    public Dfs(Pool[][] pools) {
        this.maxEatCnt = -1;
        this.maxEatRoute = new int[SHARK_MOVE_CNT];
        this.pools = pools;
    }

    public int getMaxEatCnt() {
        return maxEatCnt;
    }

    public Pool[][] getPools() {
        return pools;
    }

    public void setMaxEatCnt(int maxEatCnt) {
        this.maxEatCnt = maxEatCnt;
    }

    public int[] getMaxEatRoute() {
        return maxEatRoute;
    }

    public void setMaxEatRoute(int[] maxEatRoute) {
        this.maxEatRoute = Arrays.copyOf(maxEatRoute, SHARK_MOVE_CNT);
    }

    public void search(int y, int x, boolean[][] isEaten, int moveCnt, int eatCnt, int[] eatRoute) {
        if (moveCnt == SHARK_MOVE_CNT) {
            if (getMaxEatCnt() < eatCnt) {
                setMaxEatCnt(eatCnt);
                setMaxEatRoute(eatRoute);
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextY = y + SHARK_MOVE_Y[i];
            int nextX = x + SHARK_MOVE_X[i];
            if (-1 < nextY && nextY < Main.BOARD_SIZE && -1 < nextX && nextX < Main.BOARD_SIZE) {
                eatRoute[moveCnt] = i;
                if (!isEaten[nextY][nextX]) {
                    isEaten[nextY][nextX] = true;
                    search(nextY, nextX, isEaten, moveCnt + 1, eatCnt + getPools()[nextY][nextX].getFishes().size(), eatRoute);
                    isEaten[nextY][nextX] = false;
                } else {
                    search(nextY, nextX, isEaten, moveCnt + 1, eatCnt, eatRoute);
                }

                eatRoute[moveCnt] = -1;
            }
        }

    }
}

public class Main {
    public static final int BOARD_SIZE = 4;
    private static final int[] MOVE_Y = {0, -1, -1, -1, 0, 1, 1, 1};
    private static final int[] MOVE_X = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        Pool[][] pools = Pool.makeEmptyPools(BOARD_SIZE);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int fy = Integer.parseInt(st.nextToken()) - 1;
            int fx = Integer.parseInt(st.nextToken()) - 1;
            int fd = Integer.parseInt(st.nextToken()) - 1;
            pools[fy][fx].addFish(fd);
        }
        st = new StringTokenizer(br.readLine());
        int sy = Integer.parseInt(st.nextToken()) - 1;
        int sx = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0; i < s; i++) {
            Pool[][] copyPools = Pool.copyPools(BOARD_SIZE, pools);
            Pool[][] nextPools = Pool.makeEmptyPoolsRemainSmell(BOARD_SIZE, pools);

            for (int j = 0; j < BOARD_SIZE; j++) {
                for (int k = 0; k < BOARD_SIZE; k++) {
                    for (int fishDir : pools[j][k].getFishes()) {
                        int originDir = fishDir;
                        boolean isMove = false;
                        for (int l = 0; l < 8; l++) {
                            int nextY = j + MOVE_Y[fishDir];
                            int nextX = k + MOVE_X[fishDir];
                            if (-1 < nextY && nextY < BOARD_SIZE && -1 < nextX && nextX < BOARD_SIZE) {
                                if (!(nextY == sy && nextX == sx) && pools[nextY][nextX].getSmell() == 0) {
                                    isMove = true;
                                    nextPools[nextY][nextX].addFish(fishDir);
                                    break;
                                }
                            }
                            if (fishDir == 0) {
                                fishDir = 7;
                            } else {
                                fishDir--;
                            }

                        }

                        if (!isMove) {
                            nextPools[j][k].addFish(originDir);
                        }
                    }
                }
            }

            Dfs dfs = new Dfs(nextPools);
            int[] eatRoute = new int[Dfs.SHARK_MOVE_CNT];
            Arrays.fill(eatRoute, -1);
            boolean[][] dup = new boolean[BOARD_SIZE][BOARD_SIZE];
            dfs.search(sy, sx, dup, 0, 0, eatRoute);
            for (int sharkDir : dfs.getMaxEatRoute()) {
                sy += Dfs.SHARK_MOVE_Y[sharkDir];
                sx += Dfs.SHARK_MOVE_X[sharkDir];
                nextPools[sy][sx].eatFishes();
            }

            Pool.cleanUp(BOARD_SIZE, nextPools);

            Pool.copyFishes(BOARD_SIZE, copyPools, nextPools);

            pools = nextPools;
        }

        System.out.println(Pool.getFishCntInPools(BOARD_SIZE, pools));

    }
}