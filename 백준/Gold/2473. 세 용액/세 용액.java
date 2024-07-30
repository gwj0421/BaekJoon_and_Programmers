import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] liquid = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        Arrays.sort(liquid);

        long minValue = Long.MAX_VALUE;
        int[] answer = new int[3];
        for (int fixIdx = 0; fixIdx < n - 2; fixIdx++) {
            long fixValue = liquid[fixIdx];
            int left = fixIdx + 1;
            int right = n - 1;
            while (left < right) {
                long temp = fixValue + liquid[left] + liquid[right];
                if (Math.abs(temp) < minValue) {
                    minValue = Math.abs(temp);
                    answer = new int[]{fixIdx, left, right};
                }
                if (temp > 0) {
                    right--;
                } else if (temp < 0) {
                    left++;
                } else {
                    break;
                }
            }
        }
        System.out.println(liquid[answer[0]] + " " + liquid[answer[1]] + " " + liquid[answer[2]]);

    }

}
