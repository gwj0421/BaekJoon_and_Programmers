import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Belt {
    private int durability;
    private boolean isRobot;

    public Belt(int durability, boolean isRobot) {
        this.durability = durability;
        this.isRobot = isRobot;
    }

    public int getDurability() {
        return durability;
    }

    public boolean isRobot() {
        return isRobot;
    }

    public void upRobot() {
        this.isRobot = true;
    }

    public void downRobot() {
        this.isRobot = false;
    }

    public void downDurability() {
        this.durability--;
    }

    @Override
    public String toString() {
        return "Belt{" +
                "durability=" + durability +
                ", isRobot=" + isRobot +
                '}';
    }
}

public class Main {
    private static int N;
    private static int K;

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        List<Belt> belts = new LinkedList<>();
        String[] durability = br.readLine().split(" ");
        for (int i = 0; i < 2 * N; i++) {
            belts.add(new Belt(Integer.parseInt(durability[i]), false));
        }
        int step = 1;
        while (true) {
            belts.add(0, belts.remove(2 * N - 1));
            if (belts.get(N - 1).isRobot()) {
                belts.get(N - 1).downRobot();
            }
            for (int i = N - 2; i > -1; i--) {
                if (belts.get(i).isRobot() && !belts.get(i + 1).isRobot() && belts.get(i + 1).getDurability() > 0) {
                    belts.get(i).downRobot();
                    belts.get(i + 1).upRobot();
                    belts.get(i + 1).downDurability();
                }
            }
            if (belts.get(N - 1).isRobot()) {
                belts.get(N - 1).downRobot();
            }
            if (belts.get(0).getDurability() != 0) {
                belts.get(0).upRobot();
                belts.get(0).downDurability();
            }
            int zeroCnt = 0;
            for (Belt belt : belts) {
                if (belt.getDurability() == 0) {
                    zeroCnt++;
                }
            }
            if (zeroCnt >= K) {
                break;
            } else {
                step++;
            }

        }


        System.out.println(step);
    }


}