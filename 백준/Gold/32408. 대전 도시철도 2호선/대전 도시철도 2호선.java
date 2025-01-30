import java.util.*;

public class Main {
    static long answer = 0;
    static int[] up, size, way;
    static int N;
    static List<Integer>[] load;

    static int dfs(int node, int prev) {
        size[node] = 1;
        for (int next : load[node]) {
            if (next != prev) {
                size[node] += dfs(next, node);
            }
        }
        return size[node];
    }

    static void dfs2(int node, int prev) {
        for (int next : load[node]) {
            if (next != prev) {
                up[next] = node;
                dfs2(next, node);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        
        up = new int[N + 1];
        size = new int[N + 1];
        way = new int[N + 1];
        load = new ArrayList[N + 1];
        
        for (int i = 1; i <= N; i++) {
            load[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            load[u].add(v);
            load[v].add(u);
        }
        sc.close();
        
        dfs2(1, 0);
        
        int temp = N;
        while (temp != 0) {
            way[temp] = 1;
            temp = up[temp];
        }
        
        List<Long> vc = new ArrayList<>();
        
        for (int i = 1; i <= N; i++) {
            if (way[i] == 0) continue;
            for (int next : load[i]) {
                if (way[next] == 0) {
                    vc.add((long) dfs(next, i));
                }
            }
        }
        
        long prefix = 0;
        for (long e : vc) {
            answer += prefix * e;
            prefix += e;
        }
        
        System.out.println(answer);
    }
}
