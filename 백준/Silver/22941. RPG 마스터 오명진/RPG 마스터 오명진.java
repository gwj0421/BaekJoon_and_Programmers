import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int heroHp = Integer.parseInt(st.nextToken());
        int heroAtk = Integer.parseInt(st.nextToken());
        long demonHp = Long.parseLong(st.nextToken());
        int demonAtk = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        Game game = new Game();
        game.expectResult(heroHp, heroAtk, demonHp, demonAtk, p, s);
    }

    static class Game {

        void expectResult(int heroHp, int heroAtk, long demonHp, int demonAtk, int p, int s) {
            if (canActivateHealSkill(heroAtk, demonHp, p) || heroAtk == 1) {
                demonHp += s;
            }
            long heroWinTurn = demonHp % heroAtk == 0 ? demonHp / heroAtk : (demonHp / heroAtk + 1);
            int demonWinTurn = calculateWinTurn(heroHp, demonAtk);
            if (heroWinTurn <= demonWinTurn) {
                System.out.println("Victory!");
            } else {
                System.out.println("gg");
            }
        }


        int calculateWinTurn(int targetHp, int atk) {
            return targetHp % atk == 0 ? targetHp / atk : (targetHp / atk + 1);
        }

        boolean canActivateHealSkill(int heroAtk, long demonHp, int p) {
            long remainDemonHp = demonHp % heroAtk;
            return 1 <= remainDemonHp && remainDemonHp <= p;
        }
    }
}


