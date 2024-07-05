import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
        String[] result = new String[]{
                "         ,r'\"7",
                "r`-_   ,'  ,/",
                " \\. \". L_r'",
                "   `~\\/",
                "      |",
                "      |"
        };
        for (String s : result) {
            System.out.println(s);
        }
    }
}
