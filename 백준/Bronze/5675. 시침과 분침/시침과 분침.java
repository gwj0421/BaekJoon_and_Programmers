import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        Set<Integer> ableAngle = new HashSet<>();
        int hourAngle = 0, minAngle = 0;

        for (int hour = 0; hour < 24; hour++) {
            for (int min = 0; min < 60; min++) {
                minAngle = (minAngle + 6) % 360;
                if (minAngle % 72 == 0) {
                    hourAngle = (hourAngle + 6) % 360;
                }
                ableAngle.add(Math.min(hourAngle - minAngle, 360 - (hourAngle - minAngle)));
            }
        }
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int angle = sc.nextInt();
            if (ableAngle.contains(angle)) {
                System.out.println("Y");
            } else {
                System.out.println("N");
            }

        }
    }

    public static void calculateAngle(Set<Integer> ableAngle, int hour, int min) {
        int tmpAngle = Math.abs(hour - min) * 6;
        if (tmpAngle > 180) {
            tmpAngle = (60 - Math.max(hour, min) + Math.min(hour, min)) * 6;
        }
        ableAngle.add(tmpAngle);
//        ableAngle.add(360 - Math.abs(hour - min) * 6);
    }

}