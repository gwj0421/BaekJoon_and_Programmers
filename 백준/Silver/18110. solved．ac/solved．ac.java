import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if (n == 0) {
            System.out.println(0);
            return;
        }
        int[] score = new int[31];
        int total = 0;
        for (int i = 0; i < n; i++) {
            int judgeScore = Integer.parseInt(br.readLine());
            score[judgeScore]++;
            total += judgeScore;
        }
        showTrimmedAverage(n, score,total);
    }

    public static void showTrimmedAverage(int n, int[] score, int total) {
        int dropCnt = Math.round(n * 0.15F);

        int leftIdx = 0;
        int leftDropCnt = 0;
        while (leftDropCnt < dropCnt) {
            if (score[leftIdx] > 0) {
                score[leftIdx]--;
                total -= leftIdx;
                leftDropCnt++;
            } else {
                leftIdx++;
            }
        }

        int rightIdx = score.length - 1;
        int rightDropCnt = 0;
        while (rightDropCnt < dropCnt) {
            if (score[rightIdx] > 0) {
                score[rightIdx]--;
                total -= rightIdx;
                rightDropCnt++;
            } else {
                rightIdx--;
            }
        }

        System.out.println(Math.round((float) total/ (n - 2 * dropCnt)));

    }

}
