import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());

        List<Integer> height = new ArrayList<>();
        height.add(0);
        height.add(m);
        List<Integer> width = new ArrayList<>();
        width.add(0);
        width.add(n);

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int pos = Integer.parseInt(st.nextToken());
            if (type == 1) {
                width.add(pos);
            } else {
                height.add(pos);
            }
        }

        width.sort(Comparator.naturalOrder());
        height.sort(Comparator.naturalOrder());
        
        List<Integer> temp = new ArrayList<>();
        List<Integer> temp2 = new ArrayList<>();
        for (int i = 0; i < width.size() - 1; i++) {
            temp.add(width.get(i + 1) - width.get(i));
        }
        for (int i = 0; i < height.size() - 1; i++) {
            temp2.add(height.get(i + 1) - height.get(i));
        }
        System.out.println(Collections.max(temp) * Collections.max(temp2));
    }
}

