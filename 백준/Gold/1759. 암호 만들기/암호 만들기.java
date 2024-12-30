import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        String[] alphabet = br.readLine().split(" ");
        PasswordEncoder passwordEncoder = new PasswordEncoder(l, c, alphabet);
        passwordEncoder.encode();
    }

    static class PasswordEncoder {
        private static final Set<Character> VOWELS = Set.of('a', 'e', 'i', 'o', 'u');
        private final int l;
        private final int c;
        private final String[] alphabet;
        private List<String> ablePasswords;

        public PasswordEncoder(int l, int c, String[] alphabet) {
            this.l = l;
            this.c = c;
            Arrays.sort(alphabet);
            this.alphabet = alphabet;
        }

        public void setAblePasswords(List<String> ablePasswords) {
            this.ablePasswords = ablePasswords;
        }

        public void encode() {
            setAblePasswords(new ArrayList<>());
            dfs(0, "");
            System.out.println(ablePasswords.stream().collect(Collectors.joining("\n")));
        }

        private void dfs(int depth, String comb) {
            if (depth == c) {
                if (checkPasswordCondition(comb)) {
                    ablePasswords.add(comb);
                }
                return;
            }

            dfs(depth + 1, comb + alphabet[depth]);
            dfs(depth + 1, comb);
        }

        private boolean checkPasswordCondition(String rawPassword) {
            int vowelCnt = 0;
            for (int i = 0; i < rawPassword.length(); i++) {
                if (VOWELS.contains(rawPassword.charAt(i))) {
                    vowelCnt++;
                }
            }
            return rawPassword.length() == l && vowelCnt >= 1 && rawPassword.length() - vowelCnt >= 2;
        }

    }
}