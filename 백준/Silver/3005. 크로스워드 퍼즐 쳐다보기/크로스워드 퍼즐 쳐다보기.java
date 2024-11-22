import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        char[][] board = new char[r][c];

        List<String> total = new ArrayList<>();
        String[] widthComb = new String[r];
        String[] lengthComb = new String[c];
        Arrays.fill(lengthComb, "");
        for (int i = 0; i < r; i++) {
            String inputLine = br.readLine();
            total.addAll(Arrays.asList(inputLine.split("#")));
            widthComb[i] = inputLine;
            for (int j = 0; j < c; j++) {
                lengthComb[j] += inputLine.charAt(j);
            }
        }
        for (int i = 0; i < c; i++) {
            total.addAll(Arrays.asList(lengthComb[i].split("#")));
        }
        total.sort(String::compareTo);
        for (int i = 0; i < total.size(); i++) {
            if (total.get(i).length() > 1) {
                System.out.println(total.get(i));
                return;
            }
        }
        throw new IllegalArgumentException();
    }

}


