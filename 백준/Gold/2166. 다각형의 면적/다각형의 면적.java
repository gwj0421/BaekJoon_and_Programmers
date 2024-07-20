import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Coordinate[] polygon = new Coordinate[n + 1];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            polygon[i] = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        polygon[n] = new Coordinate(polygon[0].getX(), polygon[0].getY());
        System.out.printf("%.1f", Coordinate.getArea(polygon));

    }

    static class Coordinate {
        private final long x;
        private final long y;

        public Coordinate(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public long getY() {
            return y;
        }

        public long getX() {
            return x;
        }

        public static double getArea(Coordinate[] coordinates) {
            long ans = 0;
            for (int i = 0; i < coordinates.length - 1; i++) {
                ans += coordinates[i].getX() * coordinates[i + 1].getY()
                        - coordinates[i + 1].getX() * coordinates[i].getY();
            }

            return Math.abs(ans / 2.0);
        }
    }
}
