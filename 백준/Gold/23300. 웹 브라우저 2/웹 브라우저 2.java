import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        Page page = new Page(n);
        for (int i = 0; i < q; i++) {
            page.control(br.readLine());
        }
        // now 2
        // before 1 1 2 1 1
        // after
        page.printResult();
    }

    static class Page {
        private final int websiteCnt;
        private Deque<Integer> before;
        private int now;
        private Stack<Integer> after;

        public Page(int websiteCnt) {
            this.websiteCnt = websiteCnt;
            this.before = new ArrayDeque<>();
            this.now = 0;
            this.after = new Stack<>();
        }

        public void setBefore(Deque<Integer> before) {
            this.before = before;
        }

        public void setNow(int now) {
            this.now = now;
        }

        private void clearBefore() {
            this.before.clear();
        }

        private void clearAfter() {
            this.after.clear();
        }

        public void control(String query) {
            if (query.charAt(0) == 'B') {
                back();
            } else if (query.charAt(0) == 'F') {
                front();
            } else if (query.charAt(0) == 'A') {
                access(Integer.parseInt(query.split(" ")[1]));
            } else if (query.charAt(0) == 'C') {
                compress();
            } else {
                throw new IllegalArgumentException();
            }
        }

        private void back() {
            if (before.isEmpty()) {
                return;
            }
            after.add(now);
            setNow(before.pollLast());
        }

        private void front() {
            if (after.isEmpty()) {
                return;
            }
            before.addLast(now);
            setNow(after.pop());
        }

        private void access(int i) {
            clearAfter();
            if (now != 0) {
                before.addLast(now);
            }
            setNow(i);
        }

        private void compress() {
            if (before.isEmpty()) {
                return;
            }
            Deque<Integer> temp = new ArrayDeque<>();
            while (!before.isEmpty()) {
                int top = before.pollLast();
                if (temp.isEmpty() || temp.peekFirst() != top) {
                    temp.addFirst(top);
                }
            }

            setBefore(temp);
        }

        void printResult() {
            StringBuilder sb;
            System.out.println(now);
            if (before.isEmpty()) {
                System.out.println(-1);
            } else {
                sb = new StringBuilder();
                while (!before.isEmpty()) {
                    sb.append(before.pollLast()).append(" ");
                }
                System.out.println(sb);
            }

            if (after.isEmpty()) {
                System.out.println(-1);
            } else {
                sb = new StringBuilder();
                while (!after.isEmpty()) {
                    sb.append(after.pop()).append(" ");
                }
                System.out.println(sb);
            }
        }

    }
}