import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        StringBuilder b = new StringBuilder(br.readLine());
        while (a.length() < b.length()) {
            if (peekStringBuilder(b) != 'A') {
                popStringBuilder(b);
                b.reverse();
            } else {
                popStringBuilder(b);
            }
        }
        System.out.println(a.equals(b.toString())? "1":"0");
    }

    public static char peekStringBuilder(StringBuilder stringBuilder) {
        return stringBuilder.charAt(stringBuilder.length() - 1);
    }
    public static void popStringBuilder(StringBuilder stringBuilder) {
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
    }

}