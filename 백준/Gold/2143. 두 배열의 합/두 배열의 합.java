import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[] an = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int m = Integer.parseInt(br.readLine());
        int[] bm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<Integer> ableA = prepareAbleNumbers(an, n);
        List<Integer> ableB = prepareAbleNumbers(bm, m);
//        System.out.println(ableA);
//        System.out.println(ableB);
        int aIdx = 0;
        int bIdx = ableB.size() - 1;
        long ans = 0;
        while (aIdx < ableA.size() && bIdx > -1) {
//            System.out.println(aIdx + " "  + bIdx);
            int valA = ableA.get(aIdx);
            int valB = ableB.get(bIdx);
            if (valA + valB > t) {
                bIdx--;
            } else if (valA + valB < t) {
                aIdx++;
            } else {
                long aCnt = 1;
                long bCnt = 1;
                while (aIdx < ableA.size() - 1 && ableA.get(aIdx + 1) == valA) {
                    aCnt++;
                    aIdx++;
                }
                while (bIdx > 0 && ableB.get(bIdx - 1) == valB) {
                    bCnt++;
                    bIdx--;
                }
                ans += aCnt * bCnt;
//                System.out.println(aCnt * bCnt);
                aIdx++;
                bIdx--;
            }
        }
        System.out.println(ans);
    }

    public static List<Integer> prepareAbleNumbers(int[] numbers, int size) {
        List<Integer> result = new ArrayList<>();
        int tmp;
        for (int i = 0; i < size; i++) {
            tmp = 0;
            for (int j = i; j < size; j++) {
                tmp += numbers[j];
                result.add(tmp);
            }
        }
        result.sort(Comparator.naturalOrder());
        return result;
    }
}
