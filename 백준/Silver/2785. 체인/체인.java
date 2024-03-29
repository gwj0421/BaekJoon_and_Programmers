import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> chainLength = new ArrayList<>();
        while (st.hasMoreTokens()) {
            chainLength.add(Integer.parseInt(st.nextToken()));
        }
        chainLength.sort(Comparator.naturalOrder());
        Deque<Integer> chain = new LinkedList<>(chainLength);
        int ringCnt = 0;
        while (chain.size() > 1) {
            ringCnt++;
            if (chain.peekFirst() > 1) {
                chain.offerFirst(chain.pollFirst() - 1);
            } else {
                chain.pollFirst();
            }
            if (chain.size() > 1) {
                chain.addLast(chain.pollLast() + chain.pollLast());
            } else {
                break;
            }
        }
        System.out.println(ringCnt);

    }
}