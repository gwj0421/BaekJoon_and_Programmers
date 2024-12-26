import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int[] geminisScore = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] gulliversScore = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Team geminis = new Team();
        Team gulliver = new Team();

        for (int i = 0; i < 9; i++) {
            geminis.addScore(geminisScore[i], gulliver);
            gulliver.addScore(gulliversScore[i], geminis);
        }
        if (geminis.isWinAtLeastOnce && geminis.getScore() < gulliver.getScore()) {
            System.out.println("Yes");
            return;
        }
        System.out.println("No");
    }

    static class Team {
        private int score;
        private boolean isWinAtLeastOnce;

        public Team() {
            this.score = 0;
            this.isWinAtLeastOnce = false;
        }

        public int getScore() {
            return score;
        }

        public boolean isWinAtLeastOnce() {
            return isWinAtLeastOnce;
        }


        public void addScore(int score, Team anotherTeam) {
            this.score += score;
            if (anotherTeam.getScore() < this.score) {
                this.isWinAtLeastOnce = true;
            }
        }
    }
}


