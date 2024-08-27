import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int n = Integer.parseInt(br.readLine());
        Pole[] poles = new Pole[n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            poles[i] = new Pole(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(poles);
        List<Integer> connectionB = new ArrayList<>();
        connectionB.add(poles[0].getPortB());
        int[] connectCnt = new int[n];
        connectCnt[0] = 1;
        // 1 9
        // 8 2 9 1 4 6 7 10
        for (int i = 1; i < n; i++) {
            if (connectionB.get(connectionB.size() - 1) < poles[i].getPortB()) {
                connectionB.add(poles[i].getPortB());
                connectCnt[i] = connectionB.size();
            } else {
                int idx = binarySearch(connectionB, poles[i].getPortB());
                connectionB.set(idx, poles[i].getPortB());
                connectCnt[i] = idx+1;
            }
        }

        System.out.println(n - connectionB.size());

        int connectSize = connectionB.size();
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i > -1; i--) {
            if (connectCnt[i] == connectSize) {
                connectSize--;
            } else {
                stack.push(poles[i].getPortA());
            }
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    public static int binarySearch(List<Integer> connectionB, int target) {
        int left = 0;
        int right = connectionB.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (connectionB.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
    static class Pole implements Comparable<Pole>{
        private final int portA;
        private final int portB;

        public Pole(int portA, int portB) {
            this.portA = portA;
            this.portB = portB;
        }

        public int getPortA() {
            return portA;
        }

        public int getPortB() {
            return portB;
        }

        @Override
        public int compareTo(Pole o) {
            return Integer.compare(this.portA,o.getPortA());
        }
    }
}



