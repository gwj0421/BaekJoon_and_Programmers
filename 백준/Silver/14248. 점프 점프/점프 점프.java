import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));

        int n = Integer.parseInt(br.readLine());
        int[] stoneBridge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int st = Integer.parseInt(br.readLine()) - 1;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(st);
        boolean[] visit = new boolean[n];
        visit[st] = true;

        int moveCnt = 1;
        while (!queue.isEmpty()) {
            int now = queue.poll();

            if (now - stoneBridge[now] > -1 && !visit[now - stoneBridge[now]]) {
                visit[now - stoneBridge[now]] = true;
                queue.add(now - stoneBridge[now]);
                moveCnt++;
            }
            if (now + stoneBridge[now] < n && !visit[now + stoneBridge[now]]) {
                visit[now + stoneBridge[now]] = true;
                queue.add(now + stoneBridge[now]);
                moveCnt++;
            }
        }

        System.out.println(moveCnt);
    }
}


