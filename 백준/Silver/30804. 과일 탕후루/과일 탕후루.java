import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer> tangHuLu = new ArrayList<>();
        String[] fruitInfo = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            tangHuLu.add(Integer.parseInt(fruitInfo[i]));
        }

        int[] fruitTypeCnt = new int[10];
        int leftIdx = 0, rightIdx = 0;
        int answer = 0;
        while (rightIdx < n) {
            fruitTypeCnt[tangHuLu.get(rightIdx++)]++;
            while (getFruitTypeCnt(fruitTypeCnt) > 2) {
                fruitTypeCnt[tangHuLu.get(leftIdx++)]--;
            }
            answer = Math.max(answer, rightIdx - leftIdx);
        }

        System.out.println(answer);
    }


    public static int getFruitTypeCnt(int[] fruitTypeCntArr) {
        int nowFruitTypeCnt = 0;
        for (int fruitTypeCnt : fruitTypeCntArr) {
            if (fruitTypeCnt != 0) {
                nowFruitTypeCnt++;
            }
        }
        return nowFruitTypeCnt;
    }

}
