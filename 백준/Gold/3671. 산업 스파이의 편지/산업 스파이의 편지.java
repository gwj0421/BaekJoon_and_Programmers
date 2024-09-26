import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int t = Integer.parseInt(br.readLine());
        Converter converter;
        for (int i = 0; i < t; i++) {
            converter = new Converter(Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray());
            converter.activate();
        }
    }

    static class Converter {
        private int[] numbers;
        private Set<Integer> ablePrime;

        public Converter(int[] numbers) {
            this.numbers = numbers;
            this.ablePrime = new HashSet<>();
        }

        public void activate() {
            dfs(new boolean[numbers.length], 0);
            System.out.println(ablePrime.size());
        }

        private void dfs(boolean[] visit,int combinedNumbers) {
            if (!ablePrime.contains(combinedNumbers)&&checkPrime(combinedNumbers)) {
                ablePrime.add(combinedNumbers);
            }
            for (int i = 0; i < numbers.length; i++) {
                if (!visit[i]) {
                    visit[i] = true;
                    dfs(visit, combinedNumbers * 10 + numbers[i]);
                    visit[i] = false;
                }
            }
        }

        private boolean checkPrime(int number) {
            if (number < 2) {
                return false;
            }
            for (int i = 2; i * i <= number; i++) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }

}