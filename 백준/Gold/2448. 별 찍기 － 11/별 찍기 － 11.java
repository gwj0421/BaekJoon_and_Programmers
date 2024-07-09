import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);

//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<List<String>> star = new ArrayList<>();
        Map<Integer, List<String>> starMap = new HashMap<>();
        star.add(new ArrayList<>(List.of("  *  ", " * * ", "*****")));

        for (int i = 1; i < 11; i++) {
            List<String> nowStar = star.get(i - 1);
            List<String> nextStar = new ArrayList<>();
            String topSide = " ".repeat(3 * (int) Math.pow(2,i - 1));
            for (String top : nowStar) {
                nextStar.add(topSide + top + topSide);
            }
            for (String bottom : nowStar) {
                nextStar.add(bottom + " " + bottom);
            }
            star.add(nextStar);
        }
        for (String result : star.get(getOrder(n))) {
            System.out.println(result);
        }
    }

    public static int getOrder(int n) {
        return (int) (Math.log(n / 3) / Math.log(2));
    }
}
