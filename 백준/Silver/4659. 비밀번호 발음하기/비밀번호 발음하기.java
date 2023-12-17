import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;

class CustomPasswordEncoder {
    private static final Set<String> VOWELS = Set.of("a", "i", "o", "u", "e");
    private static final Set<String> CONSONANTS = Set.of("b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z");
    private final String original;
    private final String[] password;

    public CustomPasswordEncoder(String original) {
        this.original = original;
        this.password = original.split("");
    }

    public String getOriginal() {
        return original;
    }

    public String[] getPassword() {
        return password;
    }

    public void validate() {
        if (isUsingAtLeastOneVowels() && isNotUsingSameGroupThreeTime() && isNotUsingTwiceSameLetter()) {
            System.out.println(String.format("<%s> is acceptable.", getOriginal()));
        } else {
            System.out.println(String.format("<%s> is not acceptable.", getOriginal()));
        }
    }

    public boolean isUsingAtLeastOneVowels() {
        for (String word : getPassword()) {
            if (VOWELS.contains(word)) {
                return true;
            }
        }
        return false;
    }

    public boolean isNotUsingSameGroupThreeTime() {
        for (int i = 0; i < password.length - 2; i++) {
            if (VOWELS.containsAll(List.of(getPassword()[i], getPassword()[i + 1], getPassword()[i + 2]))
                    || CONSONANTS.containsAll(List.of(getPassword()[i], getPassword()[i + 1], getPassword()[i + 2]))) {
                return false;
            }
        }
        return true;
    }

    public boolean isNotUsingTwiceSameLetter() {
        for (int i = 0; i < password.length - 1; i++) {
            if (getPassword()[i].equals(getPassword()[i + 1])) {
                if (!getPassword()[i].equals("e") && !getPassword()[i].equals("o")) {
                    return false;
                }
            }
        }
        return true;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String input = br.readLine();
            if (input.equals("end")) {
                break;
            }
            CustomPasswordEncoder encoder = new CustomPasswordEncoder(input);
            encoder.validate();
        }
    }
}