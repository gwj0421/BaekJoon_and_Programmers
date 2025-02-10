import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        String s = sc.next();
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = s.charAt(i) - 'A';
            if (result[i] + k > 25 && k > 0 && result[i] > 0) {
                k -= 26 - result[i];
                result[i] = 0;
            }
        }
        if (k > 0) {
            result[n - 1] = (result[n - 1] + k) % 26;
        }

        StringBuilder sb = new StringBuilder();
        for (int c : result) {
            sb.append((char) (c + 'A'));
        }
        System.out.println(sb.toString());

    }
}