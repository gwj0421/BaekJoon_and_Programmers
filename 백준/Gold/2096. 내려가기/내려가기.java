import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);

//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        List<Pos> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] eachLine = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                board.add(new Pos(Integer.parseInt(eachLine[j])));
            }
        }

        for (int i = 1; i < n; i++) {
            Pos leftPos = board.get(3 * i - 3);
            Pos midPos = board.get(3 * i - 2);
            Pos rightPos = board.get(3 * i - 1);
            board.get(3 * i).fill(List.of(leftPos, midPos));
            board.get(3 * i + 1).fill(List.of(leftPos, midPos, rightPos));
            board.get(3 * i + 2).fill(List.of(midPos, rightPos));
        }

        System.out.println(Math.max(board.get(3 * n - 3).getMaxValue(), Math.max(board.get(3 * n - 2).getMaxValue(), board.get(3 * n - 1).getMaxValue())) + " " +
                Math.min(board.get(3 * n - 3).getMinValue(), Math.min(board.get(3 * n - 2).getMinValue(), board.get(3 * n - 1).getMinValue())));

    }

    static class Pos {
        private int value;
        private int maxValue;
        private int minValue;

        public Pos(int value) {
            this.value = value;
            this.maxValue = value;
            this.minValue = value;
        }

        public int getValue() {
            return value;
        }

        public int getMaxValue() {
            return maxValue;
        }

        public int getMinValue() {
            return minValue;
        }

        public void setMaxValue(int maxValue) {
            this.maxValue = maxValue;
        }

        public void setMinValue(int minValue) {
            this.minValue = minValue;
        }

        public void fill(List<Pos> beforePos) {
            int beforeMinValue = Integer.MAX_VALUE;
            int beforeMaxValue = Integer.MIN_VALUE;
            for (Pos pos : beforePos) {
                if (pos.getMinValue() < beforeMinValue) {
                    beforeMinValue = pos.getMinValue();
                }
                if (pos.getMaxValue() > beforeMaxValue) {
                    beforeMaxValue = pos.getMaxValue();
                }
            }
            setMinValue(beforeMinValue + getValue());
            setMaxValue(beforeMaxValue + getValue());
        }

    }
}
