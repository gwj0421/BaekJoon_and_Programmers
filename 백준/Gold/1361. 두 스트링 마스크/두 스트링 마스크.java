import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    private static final boolean DEBUG_MODE = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br;
        if (DEBUG_MODE) {
            br = new BufferedReader(new FileReader("./input.txt"));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        String s1 = br.readLine();
        String s2 = br.readLine();
        search(new Word(s1), new Word(s2));
    }

    private static class Word {
        private Deque<Character> left;
        private Deque<Character> right;
        private String mid;

        public Word(String word) {
            String[] split = word.split("\\*");
            Deque<Character> left = new ArrayDeque<>();
            Deque<Character> right = new ArrayDeque<>();
            if (split.length == 2) {
                addCharacterToDeque(left, split[0]);
                addCharacterToDeque(right, split[1]);
            } else if (split.length == 1) {
                addCharacterToDeque(left, split[0]);
            }
            this.left = left;
            this.right = right;
            this.mid = "";
        }

        private void addCharacterToDeque(Deque<Character> target, String word) {
            for (int i = 0; i < word.length(); i++) {
                target.addLast(word.charAt(i));
            }
        }

        @Override
        public String toString() {
            return "Word{" +
                    "left=" + left +
                    ", right=" + right +
                    ", mid='" + mid + '\'' +
                    '}';
        }
    }
// [ , , SAMPLETEST ]
    // [ FOURTHSAMPLE , , ]
    // *SAMPLETEST
    // FOURTHSAMPLE*


    // [ QW , , WQ ] => [W , , W]
    // [ Q , WW, Q ] => [ , , ]
    // [ Q , ~~ , Q ]

    // W*W
    // *
    // [ LAST , ]
    // [ , LAST ]
    // LAST*
    // *LAST

    //    CA*CAB
    //    CAB*B
    // *CA
    // B*
    // [ CA , ? , B ] => [ CA , BCA , B ]
    //    [ CA , B, CAB]
    //    [ CAB , CA, B]

    // GOOD*LUCK
    // GOODS*UCK
    // *L
    // S*
    public static void search(Word s1, Word s2) {
        Word ans = new Word("");
        while (!s1.left.isEmpty() && !s2.left.isEmpty()) {
            if (s1.left.peekFirst() != s2.left.peekFirst()) {
                break;
            }
            s1.left.pollFirst();
            ans.left.addLast(s2.left.pollFirst());
        }

        while (!s1.right.isEmpty() && !s2.right.isEmpty()) {
            if (s1.right.peekLast() != s2.right.peekLast()) {
                break;
            }
            s1.right.pollLast();
            ans.right.addFirst(s2.right.pollLast());
        }

        if ((!s1.left.isEmpty() && !s2.left.isEmpty()) || (!s1.right.isEmpty() && !s2.right.isEmpty())) {
            System.out.println(-1);
            return;
        }

        if (DEBUG_MODE) {
            System.out.println(s1);
            System.out.println(s2);
        }

        // LAST*
        // *LAST

        // *LAST
        // LAST*

        // LAST*
        // *

        // *LAST
        // *
        Deque<Character> tmp1 = new ArrayDeque<>();
        Deque<Character> tmp2 = new ArrayDeque<>();

        // LASTLAST*
        // LAST*

        // LAST*
        // *
        tmp1.addAll(s1.left);
        tmp1.addAll(s2.left);
        tmp2.addAll(s1.right);
        tmp2.addAll(s2.right);
        String leftTmp = convertDeque2String(tmp1);
        String rightTmp = convertDeque2String(tmp2);
        if (tmp1.equals(tmp2)) {
            ans.mid = leftTmp;
        } else {
            if (!((!s1.left.isEmpty() && !s1.right.isEmpty()) || (!s2.left.isEmpty() && !s2.right.isEmpty()))) {
                for (int i = Math.min(leftTmp.length(), rightTmp.length()); i > 0; i--) {
                    if (rightTmp.startsWith(leftTmp.substring(leftTmp.length() - i))) {
                        ans.mid = leftTmp.substring(leftTmp.length() - i);
                        for (int j = 0; j < i; j++) {
                            tmp1.pollLast();
                            tmp2.pollFirst();
                        }
                        break;
                    }
                }
            }

            ans.mid = convertDeque2String(tmp1) + ans.mid + convertDeque2String(tmp2);
        }
        System.out.println(convertDeque2String(ans.left) + ans.mid + convertDeque2String(ans.right));
    }

    public static String convertDeque2String(Deque<Character> target) {
        StringBuilder sb = new StringBuilder();
        for (Character c : target) {
            sb.append(c);
        }
        return sb.toString();
    }
}