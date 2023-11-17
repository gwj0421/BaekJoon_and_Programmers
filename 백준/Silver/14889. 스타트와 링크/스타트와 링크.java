import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    private static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] status = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                status[i][j] = Integer.parseInt(line[j]);
            }
        }

        List<Integer> startTeam = new ArrayList<>();
        startTeam.add(0);

        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(startTeam);
        while (!queue.isEmpty()) {
            List<Integer> now = queue.poll();
            if (now.size() == n / 2) {
                calculate(now, status, n);
                continue;
            }
            for (int i = now.get(now.size() - 1) + 1; i < n; i++) {
                List<Integer> next = new ArrayList<>(now);
                next.add(i);
                queue.add(next);
            }
        }

        System.out.println(ans);
    }


    private static void calculate(List<Integer> startTeam, int[][] status, int n) {
        List<Integer> linkTeam = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!startTeam.contains(i)) {
                linkTeam.add(i);
            }
        }
        int startTeamScore = 0;
        int linkTeamScore = 0;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i + 1; j < n / 2; j++) {
                startTeamScore += status[startTeam.get(i)][startTeam.get(j)] + status[startTeam.get(j)][startTeam.get(i)];
                linkTeamScore += status[linkTeam.get(i)][linkTeam.get(j)] + status[linkTeam.get(j)][linkTeam.get(i)];
            }
        }
        ans = Math.min(ans, Math.abs(startTeamScore - linkTeamScore));
    }


}

