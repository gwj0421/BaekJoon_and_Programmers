import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Trie head = new Trie();

        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            Trie nowTrie = head;
            for (int j = 0; j < word.length(); j++) {
                char alphabet = word.charAt(j);
                if (!nowTrie.children.containsKey(alphabet)) {
                    nowTrie.children.put(alphabet, new Trie(alphabet));
                }
                nowTrie = nowTrie.children.get(alphabet);
            }
        }

        int ans = 0;
        for (int i = 0; i < m; i++) {
            String word = br.readLine();
            Trie nowTrie = head;
            int wordIdx = 0;
            for (wordIdx = 0; wordIdx < word.length(); wordIdx++) {
                char alphabet = word.charAt(wordIdx);
                if (nowTrie.children.containsKey(alphabet)) {
                    nowTrie = nowTrie.children.get(alphabet);
                } else {
                    break;
                }
            }

            if (wordIdx == word.length()) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    static class Trie {
        private char character;
        private Map<Character, Trie> children = new HashMap<>();

        public Trie() {
        }

        public Trie(char character) {
            this.character = character;
        }
    }
}


