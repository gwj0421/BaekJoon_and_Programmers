import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if (n == 0) {
            System.out.println(0);
            return;
        }
        int[] score = new int[n];
        for (int i = 0; i < n; i++) {
            score[i] = Integer.parseInt(br.readLine());
        }
        showTrimmedAverage(score);
    }

    public static void showTrimmedAverage(int[] score) {
        Arrays.sort(score);
        int dropCnt = Math.round(score.length * 0.15F);
        float sum = 0;
        for (int i = dropCnt; i < score.length - dropCnt; i++) {
            sum += score[i];
        }
        System.out.println(Math.round(sum / (score.length - 2 * dropCnt)));
    }

}
