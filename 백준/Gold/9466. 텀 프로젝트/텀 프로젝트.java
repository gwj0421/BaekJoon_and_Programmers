import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] relationship = new int[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j < n + 1; j++) {
                relationship[j] = Integer.parseInt(st.nextToken());
            }
            TeamMaker teamMaker = new TeamMaker(n, relationship);
            teamMaker.make();

        }
    }

    static class TeamMaker {
        private static final int NOT_VISIT = 0;
        private static final int VISIT = 1;
        private static final int IN_CYCLE = 2;
        private static final int OUT_CYCLE = 3;
        private final int n;
        private final int[] relationship;
        private int notMakeTeamCnt;

        public TeamMaker(int n, int[] relationship) {
            this.n = n;
            this.relationship = relationship;
            this.notMakeTeamCnt = n;
        }

        public int getN() {
            return n;
        }

        public int[] getRelationship() {
            return relationship;
        }

        public void make() {
            boolean[] visit = new boolean[getN() + 1];
            boolean[] isSearch = new boolean[getN() + 1];
            for (int i = 1; i < getN() + 1; i++) {
                if (!isSearch[i]) {
                    search(i, visit,isSearch);
                }
            }
            System.out.println(notMakeTeamCnt);
        }

        public void search(int now, boolean[] visit, boolean[] isSearch) {
            if (isSearch[now]) {
                return;
            }
            if (visit[now]) {
                isSearch[now] = true;
                notMakeTeamCnt--;
            }
            visit[now] = true;
            search(getRelationship()[now], visit, isSearch);
            isSearch[now] = true;
        }
    }

}
