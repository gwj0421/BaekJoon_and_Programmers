import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int pKill = 0;
    static boolean pFlag = false;

    static boolean DFS(int killK, int K, int nowH, boolean[] isKill, int prevR, int prevC, List<int[]> mosV) {
        pKill = Math.max(pKill, killK);
        if (killK == K) {
            return true;
        }
        for (int i = 0; i < mosV.size(); i++) {
            if (!isKill[i] && nowH >= Math.abs(prevR - mosV.get(i)[0]) + Math.abs(prevC - mosV.get(i)[1])) {
                isKill[i] = true;
                pFlag = DFS(killK + mosV.get(i)[2], K, nowH - (Math.abs(prevR - mosV.get(i)[0]) + Math.abs(prevC - mosV.get(i)[1])), isKill, mosV.get(i)[0], mosV.get(i)[1], mosV);
                if (pFlag) {
                    return true;
                }
                isKill[i] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int elecKill = 0;
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int K = scanner.nextInt();
        int T = scanner.nextInt();
        int P = scanner.nextInt();
        
        int[][] mosRC = new int[K][3];
        List<int[]> mosV = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            int r = scanner.nextInt();
            int c = scanner.nextInt();
            int s = scanner.nextInt();
            mosRC[i][0] = r;
            mosRC[i][1] = c;
            mosRC[i][2] = s;

            boolean flag = false;
            for (int j = 0; j < mosV.size(); j++) {
                if (mosV.get(j)[0] == r && mosV.get(j)[1] == c) {
                    mosV.get(j)[2] += 1;
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                mosV.add(new int[]{r, c, 1});
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                int tempKill = 0;
                for (int k = 0; k < mosRC.length; k++) {
                    if (i == mosRC[k][0] && j == mosRC[k][1]) {
                        tempKill += 1;
                    } else {
                        if (mosRC[k][2] <= P / (Math.abs(i - mosRC[k][0]) + Math.abs(j - mosRC[k][1]))) {
                            tempKill += 1;
                        }
                    }
                }
                elecKill = Math.max(elecKill, tempKill);
            }
        }

        boolean[] isKill = new boolean[mosV.size()];
        for (int i = 0; i < mosV.size(); i++) {
            isKill[i] = true;
            DFS(mosV.get(i)[2], K, T, isKill, mosV.get(i)[0], mosV.get(i)[1], mosV);
            isKill[i] = false;
        }

        System.out.println(pKill + " " + elecKill);
        scanner.close();
    }
}
