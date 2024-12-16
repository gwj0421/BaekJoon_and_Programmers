import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int n = Integer.parseInt(br.readLine());
        TPS tps = new TPS();
        for (int i = 0; i < n; i++) {
            tps.activate(br.readLine());
        }

    }

    static class TPS {
        private Coord player;
        private int cameraDir;
        private static final Map<String, Integer> DIRECTION = Map.of(
                "W", 0,
                "A", 1,
                "S", 2,
                "D", 3
        );

        public TPS() {
            this.player = new Coord(0, 0);
            this.cameraDir = 0;
        }

        public void activate(String command) {
            if (command.equals("W") || command.equals("A") || command.equals("S") || command.equals("D")) {
                player.move((DIRECTION.get(command) + cameraDir) % 4);
            } else if (command.equals("MR")) {
                if (this.cameraDir == 0) {
                    this.cameraDir = 3;
                } else {
                    this.cameraDir -= 1;
                }
            } else if (command.equals("ML")) {
                this.cameraDir = (this.cameraDir + 1) % 4;
            } else {
                throw new IllegalArgumentException();
            }

            Coord camera = player.getCameraCoord(cameraDir);
            System.out.println(String.format("%d %d %d %d", player.x, player.y, camera.x, camera.y));
        }
    }

    static class Coord {
        private static final int[][] MOVE_PATTERN = new int[][]{
                {0, 1}, {-1, 0}, {0, -1}, {1, 0}
        };
        private int x;
        private int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void move(int d) {
            this.x += MOVE_PATTERN[d][0];
            this.y += MOVE_PATTERN[d][1];
        }

        public Coord getCameraCoord(int cameraDir) {
            return new Coord(this.x + MOVE_PATTERN[(cameraDir + 2) % 4][0],
                    this.y + MOVE_PATTERN[(cameraDir + 2) % 4][1]);
        }
    }
}


