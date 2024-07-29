import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static final int MAX_CARD_NUMBER = 1000000;
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] player = new int[n];
        int[] scores = new int[MAX_CARD_NUMBER + 1];
        boolean[] card = new boolean[MAX_CARD_NUMBER + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            player[i] = Integer.parseInt(st.nextToken());
            card[player[i]] = true;
        }
        for (int playerCardNum : player) {
            for (int i = playerCardNum * 2; i < MAX_CARD_NUMBER + 1; i += playerCardNum) {
                if (card[i]) {
                    scores[i]--;
                    scores[playerCardNum]++;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int playerCard : player) {
            sb.append(scores[playerCard]).append(" ");
        }
        System.out.println(sb);
    }

    public static boolean[] getSieveOfEratosthenes() {
        boolean[] eratosthenes = new boolean[MAX_CARD_NUMBER + 1];
        eratosthenes[2] = true;
        eratosthenes[3] = true;
        for (int i = 2; i < Math.sqrt(MAX_CARD_NUMBER) + 1; i++) {
            for (int j = 2 * i; j < MAX_CARD_NUMBER + 1; j += i) {
                eratosthenes[j] = true;
            }
        }
        return eratosthenes;
    }
}
