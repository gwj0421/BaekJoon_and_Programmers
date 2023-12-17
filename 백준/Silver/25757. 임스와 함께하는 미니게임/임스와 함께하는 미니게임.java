import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static final Map<Character, Integer> NUMBER_OF_PLAYING = Map.of('Y', 1, 'F', 2, 'O', 3);

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        char gameType = st.nextToken().charAt(0);
        Set<String> players = new HashSet<>();
        for (int i = 0; i < n; i++) {
            players.add(br.readLine());
        }
        System.out.println(players.size() / NUMBER_OF_PLAYING.get(gameType));
    }
}